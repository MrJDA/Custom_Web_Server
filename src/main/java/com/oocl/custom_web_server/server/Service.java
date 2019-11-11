package com.oocl.custom_web_server.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Service extends Thread {
    private Socket socket;
    public Service(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            socket.setReceiveBufferSize(1024);
            inputStream = socket.getInputStream();
            Request request = ParseRequest.parseToRequest(inputStream);
            outputStream = socket.getOutputStream();
            Response response = new Response(outputStream);
            service(request,response, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
           if(socket != null){
               try {
                  if(inputStream != null)inputStream.close();
                  if(outputStream != null)outputStream.close();
                   socket.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
        }
    }

    private void service(Request request, Response response, Socket socket) {
        try {
            response.write("HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 23\r\n" +
                    "\r\n" +
                    "<h1>File Not Found</h1>", socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
