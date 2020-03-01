package com.daxue.socket;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Admin
 */
public class MysqlClient {
    public static void main(String[] args) {

        try {
            Socket socket = new Socket("127.0.0.1", 9090);
            socket.getOutputStream().write("hello".getBytes());
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
