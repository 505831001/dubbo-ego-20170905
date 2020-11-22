package com.ego.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author liuweiwei
 * @since 2020-09-27
 */
public class UDPToServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(8848);
        byte[] data = new byte[1024 * 64];
        DatagramPacket packet = new DatagramPacket(data, 0, data.length);
        socket.receive(packet);
        byte[] bytes = packet.getData();
        String result = new String(bytes, 0, packet.getLength());
        System.out.println(result);
        socket.close();
    }
}
