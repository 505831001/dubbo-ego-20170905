package com.ego.net;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author liuweiwei
 * @since 2020-09-27
 */
public class TCPToClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8848);
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        String message = "林一明是一个靓仔";
        output.writeUTF(message);
        output.flush();
        socket.close();
    }
}
