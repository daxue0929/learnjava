package com.daxue.socket;

public class TimeServer {

    public static void main(String[] args) {
        int port = 8081;

        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);

        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();


    }

}

