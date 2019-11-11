package com.oocl.custom_web_server.server;

import lombok.Data;

@Data
public class Request {
    private String url;
    private String uri;
    private String port;
    private String method;
    private String host;

    public String getUrl(){
        return this.host+this.uri;
    }
}
