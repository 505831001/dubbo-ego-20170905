package com.ego.net;

import com.ego.entity.TbUser;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author liuweiwei
 * @since 2020-09-27
 */
public class UDPObjectServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(8080);
        byte[] container = new byte[1024 * 64];
        DatagramPacket packet = new DatagramPacket(container, 0, container.length);
        socket.receive(packet);
        byte[] bytes = packet.getData();
        String string = new String(bytes, 0, packet.getLength());
        System.out.println(string);
        socket.close();
    }
}
