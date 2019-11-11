package com.oocl.custom_web_server;

import com.oocl.custom_web_server.server.WebServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.net.ServerSocket;

@SpringBootApplication
public class CustomWebServerApplication {
    public static void main(String[] args) {
        WebServer webServer = new WebServer();
        webServer.start();
    }

}
