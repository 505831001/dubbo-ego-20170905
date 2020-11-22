package com.ego.net;

import java.io.IOException;
import java.net.*;

/**
 * @author liuweiwei
 * @since 2020-09-27
 */
public class UDPToClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(8888);
        String message = "林一明是个可爱的靓仔";
        byte[] bytes = message.getBytes();
        DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length, new InetSocketAddress("localhost", 8848));
        socket.send(packet);
        socket.close();
    }
}
