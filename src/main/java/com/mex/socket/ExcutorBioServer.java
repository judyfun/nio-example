package com.mex.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * xuchuahao
 * on 2018/4/4.
 * <p>
 * 利用线程池，增强socket的并发性，但是依然有弊端，1，耗性能，2.当客户端突然激增到比线程池的数量还多的时候，或者线程池中的某些线程阻塞了，
 * 也会导致后面新进来的客户端阻塞
 */
public class ExcutorBioServer {

    public static void main(String[] args) throws IOException {
        ExecutorService pool = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("127.0.0.1", 9898));
        System.out.println("服务器启动了。。。");

        while (true) {
            Socket socket = server.accept();
            System.out.println("新接入一个客户端");
            pool.execute(() -> {
                handle(socket);
            });
        }

    }

    private static void handle(Socket socket) {

        try {
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            while (true) {
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println("接收到的信息为：" + new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("关闭客户端。。。");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
