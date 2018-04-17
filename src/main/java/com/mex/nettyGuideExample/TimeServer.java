package com.mex.nettyGuideExample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * xuchuahao
 * on 2018/4/16.
 */
public class TimeServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(9000);
            while (true) {
                Socket socket = server.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
