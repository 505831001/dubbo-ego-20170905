package com.ego.net;

import com.ego.entity.TbUser;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Date;

/**
 * @author liuweiwei
 * @since 2020-09-27
 */
public class UDPObjectClient {
    public static void main(String[] args) throws IOException {
        Date date = new Date();
        TbUser user = new TbUser(1992L, "林一明", "123456", "guest", "13812345678", "ming@qq.com", date, date);
        DatagramSocket socket = new DatagramSocket(8888);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(os));
        output.writeObject(user);
        output.flush();
        byte[] bytes = os.toByteArray();
        DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length, new InetSocketAddress("localhost", 8080));
    }
}
