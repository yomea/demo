package com.example.demo.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
public class FakeSignature {
    public static String generateFakeSha1(String packageName) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // 使用包名和一个固定的盐（salt）生成伪SHA1值
            String saltedPackageName = packageName + "salt";
            byte[] digest = md.digest(saltedPackageName.getBytes());
            // 将字节转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Could not find SHA-1 algorithm", e);
        }
    }
 
    public static void main(String[] args) {
        String packageName = "com.example.app";
        String fakeSha1 = generateFakeSha1(packageName);
        System.out.println("Fake SHA1: " + fakeSha1);
    }
}