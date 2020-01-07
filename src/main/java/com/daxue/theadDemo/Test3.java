package com.daxue.theadDemo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Test3 {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                Integer.valueOf(args[0]);
            }catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);

            System.out.println("The time server is start in port :" + port);
            Socket socket = null;

            while (true) {
                socket = server.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class TimeServerHandler implements Runnable{

    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        BufferedReader in  = null;
        PrintWriter out = null;

        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);

            String currentTime = null;
            String body = null;
            while (true) {
                body = in.readLine();
                if (body == null) break;
                System.out.println("The time server receive order : " + body);
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";

                out.println(currentTime);
            }

        } catch (IOException e) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (out != null) {
                out.close();
                out = null;
            }
            if (this.socket != null) {
                try {
                    this.socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                this.socket = null;
            }
            e.printStackTrace();
        }


    }
}