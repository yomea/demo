package xxx;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * @author wuzhenhong
 * @date 2025/4/23 16:48
 */
public class UrlFileSortDemo {

    public static void main(String[] args) throws IOException {
        UrlFileSortDemo test = new UrlFileSortDemo();
        // mapReduce
        test.map("URL.text", 10);
    }

    public void map(String filePath, long chunkSizeMB) throws IOException {
        // 保存被切割的文件路径
        List<String> mapFile = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            FileChannel channel = inputStream.getChannel();
            // 文件大小
            long fileSize = channel.size();
            // 要切割文件的起点
            long position = 0;
            int index = 0;
            while (position < fileSize) {
                long chunkSize = Math.min(chunkSizeMB << 20, fileSize - position);
                // 直接内存映射
                MappedByteBuffer buffer = channel.map(MapMode.READ_ONLY, position, chunkSize);

                // 检测最后一个换行符位置
                // 因为分块，有可能最后切割的行被折断了
                long endPos = this.findLastNewLine(buffer) + position;
                // 如果分割的块没有换行符号？怎么处理，10M应该不可能吧
                if(position == endPos) {

                }

                // 处理当前分块的有效数据
                String chunkFilePath = this.processChunk(buffer, index++);
                mapFile.add(chunkFilePath);
                // 分割下一块
                position = endPos + 1;
            }
        }

        this.mergeSort(mapFile);
    }

    private String processChunk(MappedByteBuffer buffer, int index) {

        Map<String, Integer> countMap = new HashMap<>();
        // 读取文件内容
        StringBuilder sb = new StringBuilder();
        while (buffer.hasRemaining()) {
            char c = (char) buffer.get();
            if (c == '\n') {
                String url = sb.toString().trim();
                if (url.length() > 0) {
                    Integer count = countMap.get(url);
                    if (Objects.nonNull(count)) {
                        countMap.put(url, count + 1);
                    } else {
                        countMap.put(url, 0);
                    }
                }
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        String name = "chunk_" + index;
        try (FileOutputStream outputStream = new FileOutputStream(name)) {
            countMap.entrySet().stream().sorted(
                (Comparator<? super Entry<String, Integer>>) Entry.comparingByValue().reversed()).forEach(entry -> {
                try {
                    outputStream.write(
                        (entry.getKey() + " " + entry.getValue() + "\n").getBytes(StandardCharsets.UTF_8));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return name;
    }

    private void mergeSort(List<String> mapFile) {
        if(Objects.isNull(mapFile) || mapFile.size() <= 1) {
            return;
        }
        int index = 0;
        int mergeSize = mapFile.size() / 2;
        List<String> newString = new ArrayList<>();
        for(int step = 1; step <= mergeSize; step++) {
            String name = "chunk_merge" + index;
            int start = (step - 1) * 2;
            String path1 = mapFile.get(start);
            String path2 = mapFile.get(start + 1);
            try (FileOutputStream outputStream = new FileOutputStream(name);
                BufferedReader reader1 = new BufferedReader(new FileReader(path1));
                BufferedReader reader2 = new BufferedReader(new FileReader(path2))
            ) {
                String line1 = reader1.readLine();
                String line2 = reader2.readLine();
                if(Objects.nonNull(line1) && line1.trim().length() > 0
                && Objects.nonNull(line2) && line2.trim().length() > 0) {
                    String[] urlAndCount1 = line1.trim().split(" ");
                    String url1 = urlAndCount1[0];
                    Integer count1 = Integer.parseInt(urlAndCount1[1]);
                    String[] urlAndCount2 = line2.trim().split(" ");
                    String url2 = urlAndCount2[0];
                    Integer count2 = Integer.parseInt(urlAndCount2[1]);
                    if(count1 >= count2) {
                        outputStream.write(
                            (url1 + " " + count1 + "\n").getBytes(StandardCharsets.UTF_8));
                    } else {
                        outputStream.write(
                            (url2 + " " + count2 + "\n").getBytes(StandardCharsets.UTF_8));
                    }
                } else if(Objects.nonNull(line1) && line1.trim().length() > 0
                    && (Objects.isNull(line2) || line2.trim().length() == 0)) {
                    String[] urlAndCount1 = line1.trim().split(" ");
                    String url1 = urlAndCount1[0];
                    Integer count1 = Integer.parseInt(urlAndCount1[1]);
                    outputStream.write(
                        (url1 + " " + count1 + "\n").getBytes(StandardCharsets.UTF_8));
                } else {
                    String[] urlAndCount2 = line2.trim().split(" ");
                    String url2 = urlAndCount2[0];
                    Integer count2 = Integer.parseInt(urlAndCount2[1]);
                    outputStream.write(
                        (url2 + " " + count2 + "\n").getBytes(StandardCharsets.UTF_8));
                }
                newString.add(name);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(mapFile.size() % 2 != 0) {
                newString.add(mapFile.get(mapFile.size() - 1));
            }
            this.mergeSort(newString);
        }
    }

    private long findLastNewLine(ByteBuffer buffer) {
        // 从后到前找到最后一个换行符，返回该换行符所在的位置
        for (int i = buffer.limit() - 1; i >= 0; i--) {
            if (buffer.get(i) == '\n') {
                return i;
            }
        }
        return -1;
    }

}
