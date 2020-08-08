package com.lo.io.web.sync.block.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {
    private static final int ECHOMAX = 255;

    public static void main(String[] args) {
        byte[] sendData = "Welcome!".getBytes();
        DatagramSocket datagramSocket = null;
        try {
            // 1. create datagram socket bound to local port 8889
            datagramSocket = new DatagramSocket(8889);

            DatagramPacket receivePacket = new DatagramPacket(new byte[ECHOMAX], ECHOMAX);
            DatagramPacket sendPacket;
            while (true) {
                // 2. receive datagram packet from client, blocking until datagram packet reached
                datagramSocket.receive(receivePacket);
                System.out.println("Handling client at " + receivePacket.getSocketAddress());
                System.out.println("Client said: " + new String(receivePacket.getData()));
                // 3. send datagram packet back to client
                sendPacket = new DatagramPacket(sendData, sendData.length,
                        receivePacket.getSocketAddress());
                datagramSocket.send(sendPacket);
            }
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
