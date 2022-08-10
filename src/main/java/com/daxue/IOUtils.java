package com.daxue;

import java.io.*;
import java.util.Arrays;

/**
 * @author daxue0929
 * @date 2020/11/19
 * 学习主线：
 *  Files
 *
 *  字节流：
 *      InputStream
 *          FileInputStream
 *          StringBufferInputStream
 *          ByteArrayInputStream
 *      OutputStream
 *          FileOutputStream
 *          ByteArrayOutputStream
 *
 *
 *  字符流：
 *      Reader
 *          BufferedReader
 *          InputStreamReader
 *          StringReader
 *      Writer
 *          BufferedWriter
 *          OutputStreamWriter
 *          StringWriter
 *          CharArrayWriter
 */
public class IOUtils {

    /**
     * FileInputStream 字节数组方式读取字符串
     */
    public void readStrFromFileInputStream() {
        String filePath = "/Users/daxue0929/openSourceCodes/learnjava/src/main/resources/io_test_file/test.txt";
        File file = new File(filePath);

        try {
            /**
             * FileInputStream有三个构造方法，常用的两个
             * FileInputStream(File) 掌握
             * FileInputStream(String) 掌握
             * FileInputStream(FileDescriptor) 扩展
             *
             * 声明一个File 创建其 InputStream实例
             * 从流中读取指定的字节
             * 把读出的字节转为String
             */
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                StringBuffer buffer = new StringBuffer(); // $1:这里用到了StringBuffer，理解一下和StringBuilder的区别？
                byte[] tempbytes = new byte[1024];
                int byteread = 0;

                //$2: read() 有三个重载方法,理解其差异？
                while ((byteread=fileInputStream.read(tempbytes)) != -1) {
                    String string = new String(tempbytes, 0, byteread);
                    buffer.append(string);
                }
                System.out.println(buffer.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readFromStringBufferInputStream() {
        String string = "hello world";
        StringBufferInputStream stringBufferInputStream = new StringBufferInputStream(string);
    }

    public void readStrFromByteArrayInputStream() {
        byte[] tempByte = new byte[100];
        Arrays.fill(tempByte, (byte) 1);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(tempByte);
        byte[] result = new byte[200];
        Arrays.fill(result, (byte) 3);
        int read = byteArrayInputStream.read(result,0, tempByte.length);
        System.out.println("result:" + result);
    }




}
