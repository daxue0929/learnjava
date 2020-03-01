package com.daxue.socket;



import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * 单线程来处理并发
 * 1. read()方法有阻塞
 *
 *
 * @author Admin
 */


public class MysqlServer {
    static byte[] bs = new byte[1024];

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(9090);
            while (true) {

                Socket clientSocket = serverSocket.accept();

                
                clientSocket.getInputStream().read(bs);
                System.out.println(bs.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
