package com.daxue.IO;

import java.io.File;
import java.io.IOException;

public class FileExample {
    public static void main(String[] args) {

        createFile();
    }

    private static void createFile() {
        File f = new File("C:/Users/Administrator/Desktop/create.txt");

        try {
            f.createNewFile();//当且仅当不存在具有此抽象路径名指定名称的文件时，不可分地创建一个新的空文件
            System.out.println("该分区大小" + f.getTotalSpace()/(1024*1024*1024) + "G" );

            f.mkdirs();
//            f.delete();  //删除此抽象路径名表示的文件或目录
            System.out.println("文件名:" + f.getName());  //返回由此抽象路径名表示的文件或目录的名称。
            
            System.out.println("文件父目录字符串:" + f.getParent()); //    返回此抽象路径名父目录的路径名字符串；如果此路径名没有指定父目录，则返回 null。





        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
