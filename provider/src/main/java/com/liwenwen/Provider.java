package com.liwenwen;


import com.liwenwen.common.URL;
import com.liwenwen.protocol.HttpServer;
import com.liwenwen.register.LocalRegister;
import com.liwenwen.register.MapRemoteRegister;

public class Provider {
    public static void main(String[] args) {


        //注册
        LocalRegister.regist(HelloService.class.getName(),HelloServiceImpl.class);
        // 注册中心注册
        URL url = new URL("localhost",8080);
        MapRemoteRegister.regist(HelloService.class.getName(),url);

        // netty tomcat 网络组件接收网络请求
        //rpc 框架设计为用户想用哪个网络组件自己配
        HttpServer httpServer =new HttpServer();
        httpServer.start(url.getHostname(), url.getPort());

    }
}
