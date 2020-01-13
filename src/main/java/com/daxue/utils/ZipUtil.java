package com.daxue.utils;

import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    public static String zipFilePath = null;
    public static String jpgFile = null;
    public static String FileName = null;
    public static String suffixFile = null;
    public static long FileSize = 0; // 文件大小

    public static void zipFileNoBuffer() {
        File zipFile = new File(zipFilePath);
        try {
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            long beginTime = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                InputStream input = new FileInputStream(jpgFile);
                zipOut.putNextEntry(new ZipEntry(FileName + i));
                int len = 0;
                byte[] bytes = new byte[1024];
                while ((len = input.read(bytes)) != -1) {
                    zipOut.write(bytes, 0, len);
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println(endTime - beginTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zipFileBuffer() {
        try {
            File zipFile = new File(zipFilePath);
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOut);

            // 开始时间
            long beginTime = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(jpgFile));
                zipOut.putNextEntry(new ZipEntry(FileName + i));
                int len;
                byte[] bytes = new byte[1024];
                while ((len = bufferedInputStream.read(bytes)) != -1) {
                    bufferedOutputStream.write(bytes, 0, len);
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println(endTime - beginTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zipFileChannel() {
        long beginTime = System.currentTimeMillis();
        File zipFile = new File(zipFilePath);
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
            WritableByteChannel writableByteChannel = Channels.newChannel(zipOutputStream);
            for (int i = 0; i < 10; i++) {
                FileChannel fileChannel = new FileInputStream(jpgFile).getChannel();
                zipOutputStream.putNextEntry(new ZipEntry(i + suffixFile));
                fileChannel.transferTo(0, FileSize, writableByteChannel);
            }
            long endTime = System.currentTimeMillis();
            System.out.println(endTime - beginTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
