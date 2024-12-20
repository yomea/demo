package com.example.demo.junit;

import cn.hutool.system.oshi.OshiUtil;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;  
import oshi.hardware.GlobalMemory;  
import oshi.hardware.HWDiskStore;  
import oshi.hardware.NetworkIF;  
import oshi.hardware.PhysicalMemory;  
  
import java.util.List;
import oshi.software.os.OSFileStore;

public class OshiIOExample {  
  
    public static void main(String[] args) {
        OshiUtil.getHardware().getDiskStores();
        List<OSFileStore> diskStores =
            Optional.ofNullable(OshiUtil.getOs().getFileSystem().getFileStores()).orElse(Collections.emptyList());
        long totalSpace = 0L;
        long usedSpace = 0L;
        for (OSFileStore disk : diskStores) {
            totalSpace += disk.getTotalSpace(); // 磁盘总大小，单位：字节
            usedSpace += disk.getUsableSpace(); // 磁盘可用空间，单位：字节
        }

        if(usedSpace != 0) {
            BigDecimal io = BigDecimal.valueOf(usedSpace).divide(BigDecimal.valueOf(totalSpace), 4, BigDecimal.ROUND_HALF_UP)
                .multiply(BigDecimal.valueOf(100L));
            System.out.println(io.stripTrailingZeros().toPlainString() + "%");
        } else {
            System.out.println("0%");
        }
    }
}