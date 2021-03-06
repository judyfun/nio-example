package com.mex.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * xuchuahao
 * on 2018/4/4.
 * <p>
 * 这种方式的SocketServer每次只能处理一个客户端的线程，如果当前一个线程阻塞，那么后一个客户端就被阻塞
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

    private static void handle(Socket socket) {
        try {
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while (true) {
                // 读取数据 （阻塞）
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println("接收的信息为：" + new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("socket 关闭");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
