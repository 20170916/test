package com.lo.io.web.sync.block.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;

/**
 * 先启动UdpServer ,给客户端发消息前打断点，客户端发送消息时在断点处获取ip和端口号。
 * UdpServer2启动后给记录ip和端口号的客户端发消息，发现客户端是可接收到的。开心！！！
 *
 * 这样，即时通信既可以用udp服务器来实现无状态的架构。只需要持久化客户端ip和端口，共享给所有udp服务器就ok了。
 *
 * im集群架构下：
 * 在记录了a b两个用户的连接信息后(ip 端口号)，一个udp服务器就可以完整一次消息的收发。
 * 而websocket服务器集群中，若a b用户连接了不同的websocket服务器，
 * 一次消息的收发需要一次websocket服务器间直接或间接的通信。
 * 直接通信就是通过记录b用户连接的websocket服务器的地址，直接rpc通信，但需要需要加一个服务的注册逻辑、一个rpc框架。
 * 间接通信就是借用redis或消息中间件实现消息的订阅发布。
 * 总之，websocket服务器集群的架构实现起来会很复杂。
 *
 *
 *
 */
public class UdpServer2 {
    private static final int ECHOMAX = 255;

    public static void main(String[] args) {
        byte[] sendData = "client mask 233333!".getBytes();
        DatagramSocket datagramSocket = null;
        try {
            // 1. create datagram socket bound to local port 8889
            datagramSocket = new DatagramSocket(8880);

            //DatagramPacket receivePacket = new DatagramPacket(new byte[ECHOMAX], ECHOMAX);
            final InetAddress inetAddress = InetAddress.getByName("localhost");
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
                    inetAddress, 63248);;
            datagramSocket.send(sendPacket);
           /* while (true) {
                // 2. receive datagram packet from client, blocking until datagram packet reached
                datagramSocket.receive(receivePacket);
                System.out.println("Handling client at " + receivePacket.getSocketAddress());
                System.out.println("Client said: " + new String(receivePacket.getData()));
                // 3. send datagram packet back to client
                sendPacket = new DatagramPacket(sendData, sendData.length,
                        receivePacket.getSocketAddress());
                datagramSocket.send(sendPacket);
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. close datagram socket
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }
}
