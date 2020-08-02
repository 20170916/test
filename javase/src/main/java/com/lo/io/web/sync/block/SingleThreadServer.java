package com.lo.io.web.sync.block;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadServer {

    public static void main(String[] args) {
        byte[] buffer = new byte[2];
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("服务器 已启动并监听8080端口");
            while (true) {
                System.out.println(1);
                System.out.println("服务器 正在等待连接...");
                //阻塞1：等待连接时阻塞
                Socket socket = serverSocket.accept();

                System.out.println(2);
                System.out.println("服务器 已接收到连接请求...");
                System.out.println("服务器 正在等待数据...");
                //阻塞2：等待数据时阻塞
                final int read = socket.getInputStream().read(buffer);
                System.out.println("read:"+read);

                socket.getOutputStream().write("response".getBytes());


                System.out.println(3);
                System.out.println("服务器 已经接收到数据");
                String content = new String(buffer);
                System.out.println("接收到的数据:" + content);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
