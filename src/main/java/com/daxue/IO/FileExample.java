package com.daxue.IO;



import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class FileExample {
    public static void main(String[] args) {

//        createFile();


        File file = new File("C:\\server\\data\\dlpxqjvnavi/168.0.100:15420\\29411188\\tour.xml");

        if (file.exists()) {
            System.out.println("成功");
        }

        new FileExample().test003();
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

    @Test
    public void test001(){

        String url = "http://192.168.0.100:15420/29411188/tour.html";
        String substring = url.substring(0,url.indexOf("tour.html"));
        
        System.out.println(substring);


    }

    @Test
    public void test003(){
        boolean isWin = false;
        Properties properties = System.getProperties();
        isWin = properties.getProperty("sun.desktop").indexOf("win") != -1 ? true : false;
        System.out.println(isWin);

    }
}
