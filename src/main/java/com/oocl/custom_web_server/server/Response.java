package com.oocl.custom_web_server.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Response {
    private OutputStream outputStream;

    public Response(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    public void write(String text, Socket socket) throws IOException {
        String html = "http/1.1 200 ok\n"
                +"\n\n"
                +"Hello a啊";
        outputStream.write(text.getBytes());
        outputStream.flush();
        System.out.println("完成输出");
        socket.shutdownOutput();
    }
}
