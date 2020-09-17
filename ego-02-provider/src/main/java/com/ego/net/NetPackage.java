package com.ego.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

/**
 * 1. IP 定位一个节点，计算机，路由，通讯设备等等
 * 2. InetAddress 这个类表示一个互联网协议(IP)地址。
 * 2.1 getLocalHost() 返回本地主机的地址。
 * 2.2 getByName(String host) 给定主机的名称，确定主机的IP地址。
 * 3. Port 端口。
 * 4.1 InetSocketAddress(int port) 创建一个套接字地址，其中IP地址为通配符地址，端口号为指定值。
 * 4.2 InetSocketAddress(String hostname, int port) 根据IP地址和端口号创建套接字地址。
 * 5. URL 表示一个统一的资源定位器，一个指向万维网上"资源"的指针。
 * 5.1
 *
 * @author liuweiwei
 * @since 2020-09-04
 */
public class NetPackage {
    protected static InetAddress address;
    protected static InetSocketAddress socketAddress;
    protected static URL url;
    protected static HttpURLConnection connect;

    private static InputStream is;
    private static BufferedReader reader;
    private static String message = "";

    public static void main(String[] args) throws IOException {
        /**
         * 返回本地主机的地址。
         * 这是通过从系统中检索主机名称，然后将该名称解析为{@code InetAddress}来实现的。
         */
        address = InetAddress.getLocalHost();
        System.out.println(address.getHostAddress());
        System.out.println(address.getHostName());
        address = InetAddress.getByName("localhost");
        System.out.println(address.getHostAddress());
        System.out.println(address.getHostName());
        address = InetAddress.getByName("127.0.0.1");
        System.out.println(address.getHostAddress());
        System.out.println(address.getHostName());

        /**
         * 这个类实现了一个IP套接字地址(IP地址+端口号)，它也可以是一对(主机名+端口号)。
         */
        socketAddress = new InetSocketAddress("localhost", 8088);
        System.out.println(socketAddress.getHostName());
        System.out.println(socketAddress.getAddress());
        socketAddress = new InetSocketAddress("127.0.0.1", 8088);
        System.out.println(socketAddress.getPort());

        /**
         * {@code URL}表示一个统一的资源定位器，一个指向万维网上"资源"的指针。
         */
        url = new URL("https://hao.360.com/");
        is = url.openStream();
        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        message = "";
        while ((message = reader.readLine()) != null) {
            System.out.println(message);
        }
        reader.close();
        is.close();

        connect = (HttpURLConnection) url.openConnection();
        connect.setRequestMethod("GET");
        connect.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36");
        reader = new BufferedReader(new InputStreamReader(connect.getInputStream(), "UTF-8"));
        message = "";
        while ((message = reader.readLine()) != null) {
            System.out.println(message);
        }
        reader.close();
    }
}
