package com.daxue.IO;

import java.io.*;

public class FileCount {
    public static void main(String[] args) {
        int count = 0; //统计文件字节长度
        byte[] buffer = new byte[512];
        int numberRead = 0;
        FileInputStream input = null;
        FileOutputStream out = null;

        try {
            String path = "C:/Users/Administrator/Desktop/tt.jpg";
            
            System.out.println(File.pathSeparator);
//            System.out.println(File.pathSeparatorChar);
            System.out.println(File.separator);
//            System.out.println(File.separatorChar);
            try {
                input = new FileInputStream(new File(path));
            } catch (FileNotFoundException e) {
                File file = new File(path);


                System.out.println(file.canExecute());

                if(!file.exists()){          //判断此抽象路径名是否存在
                    file.createNewFile();
                }
                input = new FileInputStream(file);
            }

            out=new FileOutputStream("C:/Users/Administrator/Desktop/tt2.jpg"); //如果文件不存在会自动创建


            while ((numberRead = input.read(buffer)) != -1){
                count ++ ;
                out.write(buffer, 0, numberRead);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println(count);
            try {
                input.close();
                out.close();
            }catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

}
