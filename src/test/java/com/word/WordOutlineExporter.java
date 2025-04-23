package com.word;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordOutlineExporter {

    // 模拟从某个地方获取的大纲数据
    private static List<Map<String, String>> getOutlineData() {
        List<Map<String, String>> outline = new ArrayList<>();
        Map<String, String> entry1 = new HashMap<>();
        entry1.put("title", "1. 章节1");
        entry1.put("content", "这是章节1的内容。");
        entry1.put("level", "1");
        outline.add(entry1);

        Map<String, String> entry11 = new HashMap<>();
        entry11.put("title", "1.1 子章节1.1");
        entry11.put("content", "这是子章节1.1的内容。");
        entry11.put("level", "2");
        outline.add(entry11);

        Map<String, String> entry12 = new HashMap<>();
        entry12.put("title", "1.2 子章节1.2");
        entry12.put("content", "这是子章节1.2的内容。");
        entry12.put("level", "2");
        outline.add(entry12);

        Map<String, String> entry2 = new HashMap<>();
        entry2.put("title", "2. 章节2");
        entry2.put("content", "这是章节2的内容。");
        entry2.put("level", "1");
        outline.add(entry2);

        Map<String, String> entry21 = new HashMap<>();
        entry21.put("title", "2.1 子章节2.1");
        entry21.put("content", "这是子章节2.1的内容。");
        entry21.put("level", "2");
        outline.add(entry21);

        Map<String, String> entry22 = new HashMap<>();
        entry22.put("title", "2.1.1 子章节2.1");
        entry22.put("content", "这是子章节2.1.1的内容。");
        entry22.put("level", "3");
        outline.add(entry22);

        return outline;
    }

    public static void main(String[] args) {

        System.out.println(Integer.MAX_VALUE);
        XWPFDocument document = new XWPFDocument();

        List<Map<String, String>> outlineData = getOutlineData();

        for (Map<String, String> entry : outlineData) {
            String title = entry.get("title");
            String level = entry.get("level");

            // 根据层级设置样式
            int headingNum = Integer.parseInt(level); // 去掉前导的'.'并转换为整数
//            String prefix = StringUtils.leftPad("", headingNum * 2, " ");
            XWPFParagraph titleParagraph = document.createParagraph();
            titleParagraph.setIndentationFirstLine(400*headingNum);
            XWPFRun titleRun = titleParagraph.createRun();
            titleRun.setText(title);
            titleRun.setBold(true);
            titleRun.setFontSize(20);
        }

        try (FileOutputStream out = new FileOutputStream("D:\\work\\temp\\test\\OutlineWithLevels.docx")) {
            document.write(out);
            System.out.println("具有层级结构的Word文档已成功创建！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}