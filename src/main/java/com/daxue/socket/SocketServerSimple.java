package com.daxue.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerSimple {


    public static void main(String[] args) {
        int port = 53351;
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("server 将一直等待连接的到来");
            Socket socket = server.accept();

            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            StringBuffer sb = new StringBuffer();
            while ((len = inputStream.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, len, "UTF-8"));
            }
            System.out.println("get message from client: " + sb);
            inputStream.close();
            socket.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void gennerateCharacters(OutputStream out) throws IOException{
        int fistPrintableCharacter = 33;
        int numberOfPrintableCharacters = 94;
        int numberOfCharactersPerLine = 72;

        int start = fistPrintableCharacter;
        while (true) {
            for (int i = start; i < numberOfCharactersPerLine; i++) {
                out.write(((i - fistPrintableCharacter) % numberOfPrintableCharacters) + fistPrintableCharacter);

                out.write('\r'); //回车
                out.write('\n'); //换行
                start = ((start + 1) - fistPrintableCharacter) % numberOfPrintableCharacters + fistPrintableCharacter;

            }
        }
    }

}
