package com.lo.io.web.sync.block;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer {

    public static void main(String[] args) {
        byte[] buffer = new byte[1024];
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("服务器已启动并监听8080端口");
            while (true) {
                System.out.println();
                System.out.println("服务器正在等待连接...");
                Socket socket = serverSocket.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("服务器已接收到连接请求...");
                        System.out.println();
                        System.out.println("服务器正在等待数据...");
                        try {
                            socket.getInputStream().read(buffer);
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        System.out.println("服务器已经接收到数据");
                        System.out.println();
                        String content = new String(buffer);
                        System.out.println("接收到的数据:" + content);
                    }
                }).start();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
