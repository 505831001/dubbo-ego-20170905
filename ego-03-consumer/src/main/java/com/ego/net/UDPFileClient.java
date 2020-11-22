package com.ego.net;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * @author liuweiwei
 * @since 2020-09-27
 */
public class UDPFileClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(8888);
        InputStream     input = new FileInputStream(new File("src/photo.jpeg"));
        byte[]          bytes = IOUtils.toByteArray(input);
        DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length, new InetSocketAddress("localhost", 8848));
        socket.send(packet);
        socket.close();
    }
}
