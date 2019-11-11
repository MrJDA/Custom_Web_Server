package com.oocl.custom_web_server.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    final int port = 9090;
    private ServerSocket serverSocket;
    public WebServer(){
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
       while (true){
           try {
               Socket socket = serverSocket.accept();
               new Service(socket).start();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }
}
