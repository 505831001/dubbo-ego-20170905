package com.ego.net;

import com.ego.utils.IOFileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author liuweiwei
 * @since 2020-09-27
 */
public class UDPFileServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(8848);
        byte[] container = new byte[1024 * 64];
        DatagramPacket packet = new DatagramPacket(container, 0, container.length);
        socket.receive(packet);
        byte[] bytes = packet.getData();
        System.out.println(new String(bytes, 0, packet.getLength()));
        socket.close();
    }
}
