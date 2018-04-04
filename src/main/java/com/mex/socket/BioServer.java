package com.mex.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * xuchuahao
 * on 2018/4/4.
 */
public class BioServer {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(9898));
        System.out.println("服务器启动...");

        while (true) {
            // 获取一个套接字 （阻塞）
            Socket socket = server.accept();
            System.out.println("来了一个客户端...");

            handle(socket);
        }
    }

    private static void handle(Socket socket){
        byte[] bytes = new byte[1024];

        while (true) {
            // 读取数据 （阻塞）
            int read = 0;
            try {
                InputStream inputStream = socket.getInputStream();
                read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    System.out.println("socket 关闭");
                    socket.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

        }
    }

}
