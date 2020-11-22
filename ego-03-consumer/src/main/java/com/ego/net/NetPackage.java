package com.ego.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URL;

/**
 * 1. IP 定位一个节点，计算机，路由，通讯设备等等
 * 2. InetAddress 这个类表示一个互联网协议(IP)地址。
 * 2.1 getLocalHost() 返回本地主机的地址。
 * 2.2 getByName(String host) 给定主机的名称，确定主机的IP地址。
 * 3. Port 端口。
 * 4.1 InetSocketAddress(int port) 创建一个套接字地址，其中IP地址为通配符地址，端口号为指定值。
 * 4.2 InetSocketAddress(String hostname, int port) 根据IP地址和端口号创建套接字地址。
 * 5. URL 表示一个统一的资源定位器，一个指向万维网上"资源"的指针。
 * 5.1 URLConnection 抽象类{@code URLConnection}是所有类的超类，这些类表示应用程序和URL之间的通信链接。
 * 5.1 HttpURLConnection 支持特定于HTTP的特性的URLConnection。详情请参阅the spec。
 * 5.2 setRequestMethod(String method) 设置URL请求的方法，其中一个:GET,POST,HEAD,OPTIONS,PUT,DELETE,TRACE是合法的，受协议限制。默认方法是GET。
 * 5.3 setRequestProperty(String key, String value) 设置通用请求属性。如果具有该键的属性已经存在，则用新值覆盖其值。
 * 6. UDP - User Datagram Protocol 用户数据报协议。
 * 6.1 DatagramSocket(int port) 这个类表示用于发送和接收数据报数据包的套接字。
 * 6.1.1 send(DatagramPacket packet) 从此套接字发送数据报数据包。{@code DatagramPacket}包含指示要发送的数据、其长度、远程主机的IP地址和远程主机上的端口号的信息。
 * 6.1.2 receive(DatagramPacket packet) 从此套接字接收数据报数据包。当此方法返回时，{@code DatagramPacket}的缓冲区被接收到的数据填充。数据报包还包含发送方的IP地址和发送方机器上的端口号。
 * 6.2 DatagramPacket(byte buf[], int offset, int length, SocketAddress address) 这个类表示一个数据报包。
 * 6.2.1 byte[] getData() 返回数据缓冲区。接收到的数据或要发送的数据从缓冲区中的{@code offset}开始，运行时间为{@code length}。
 * 6.2.2 int getLength() 返回要发送的数据长度或接收到的数据长度。
 * 7. TCP - Transmission Control Protocol 传输控制协议。
 * 7.1 ServerSocket 这个类实现服务器套接字。服务器套接字等待通过网络传入的请求。它根据该请求执行一些操作，然后可能向请求者返回一个结果。
 * 7.1.1 accept() 监听与此套接字建立的连接，并接受它。该方法将阻塞，直到建立连接为止。
 * 7.2 Socket 这个类实现客户端套接字(也称为"套接字")。套接字是两台机器之间进行通信的端点。
 * 7.2.1 getInputStream() 返回此套接字的输入流。
 * 7.2.2 readUTF() 参考DataInput类的readUTF方法的通用合同。
 * 7.2.3 getOutputStream() 返回此套接字的输出流。
 * 7.2.4 writeUTF(String str) 使用DataInput修改的UTF-8编码以独立于机器的方式向底层输出流写入一个字符串。
 * 7.2.5 flush() 刷新此数据输出流。这强制将所有缓冲的输出字节写到流中。
 * 8.
 *
 * @author liuweiwei
 * @since 2020-09-04
 */
public class NetPackage {
    protected static InetAddress address;
    protected static InetSocketAddress socketAddress;
    protected static URL url;
    protected static HttpURLConnection connect;

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
        InputStream is = url.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String message = "";
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
