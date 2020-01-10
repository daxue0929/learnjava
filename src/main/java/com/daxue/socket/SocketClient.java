package com.daxue.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) {
        // 要连接的服务端IP地址和端口
        String host = "127.0.0.1";
        int port = 53351;
        // 与服务端建立连接
        Socket socket = null;
        try {
            socket = new Socket(host, port);
            // 建立连接后获得输出流
            OutputStream outputStream = socket.getOutputStream();
            String message="你好  yiwangzhibujian";
            socket.getOutputStream().write(message.getBytes("UTF-8"));
            outputStream.close();

            //socket.shutdownOutput(); // socket关闭输出流的方式, 可以告知服务端自己已经写完了, 但是不能再次发送消息给服务端,如果再次发送,需要重新建立Socket连接



            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void test(OutputStream out) throws IOException{
        int firstPrintableCharactor = 33;
        int numberOfPrintableCharacter = 94;
        int numberOfCharactersPerLine = 72;
        int start = firstPrintableCharactor;
        byte[] line = new byte[numberOfCharactersPerLine + 2];
        // +2 对应回车和换行
        while (true) {
            for (int i = start; i < start + numberOfCharactersPerLine; i++) {
                line[i - start] = (byte) ((i - numberOfCharactersPerLine) % numberOfPrintableCharacter + firstPrintableCharactor);
                line[72] = (byte) '\r'; // 回车
                line[73] = (byte) '\n'; //换行
                out.write(line);
                start = ((start - 1) - firstPrintableCharactor) % numberOfPrintableCharacter + firstPrintableCharactor;
            }
        }


    }
}
