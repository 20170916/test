package com.lo.io.web.sync.block;

import java.io.IOException;
import java.net.Socket;

public class SingleThreadClient2 {

    /**
     * 阻塞中的server不会响应客户端
     * @param args
     */
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",8080);
            socket.getOutputStream().write("data from client".getBytes());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
