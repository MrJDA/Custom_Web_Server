package com.oocl.custom_web_server.server;

import org.springframework.util.StringUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseRequest {

    public static final int MAX_READ_LENGTH = 1024;

    public static Request parseToRequest(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        byte[] bytes = new byte[MAX_READ_LENGTH];
        int readLength = 0;
        int cacheController = -1;
        StringBuilder stringBuilder = new StringBuilder();
        Pattern pattern = Pattern.compile("\r\n\r\n");
        while((readLength = bufferedInputStream.read(bytes)) != -1){
            for(int i = 0; i<readLength; i++){
                stringBuilder.append((char)bytes[i]);
            }
            //通过http协议的结束标志\r\n\r\n来退出read阻塞。
            Matcher matcher = pattern.matcher(stringBuilder.toString());
            if(matcher.find()){
                break;
//                int index = stringBuilder.toString().indexOf("\r\n", matcher.end());
//                String cacheControllerStr = stringBuilder.toString().substring(matcher.end(), index);
//                System.out.println(cacheControllerStr);
            }
        }
        if(StringUtils.isEmpty(stringBuilder.toString()))return null;
        Request request = new Request();
        String requestMessage = stringBuilder.toString();
        if(StringUtils.isEmpty(requestMessage))return null;
        System.out.println(requestMessage);
        return request;
    }

    private static String getHost(String requestMessage) {
        String[] strings = requestMessage.split("\r\n");
        String hostContent = strings[1];
        return hostContent.substring(hostContent.indexOf(" ")+1);
    }

    private static String getUri(String requestMessage) {
        int startIndex = requestMessage.indexOf(' ')+1;
        int endIndex = requestMessage.indexOf(' ', startIndex);
        return requestMessage.substring(startIndex, endIndex);
    }

    private static String getMethod(String requestMessage) {
        int index = requestMessage.indexOf(' ');
        return requestMessage.substring(0, index);
    }
}
