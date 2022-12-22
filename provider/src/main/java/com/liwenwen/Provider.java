package com.liwenwen;


import com.liwenwen.protocol.HttpServer;
import com.liwenwen.register.LocalRegister;

public class Provider {
    public static void main(String[] args) {
        //注册
        LocalRegister.regist(HelloService.class.getName(),HelloServiceImpl.class);

        // netty tomcat 网络组件接收网络请求
        //rpc 框架设计为用户想用哪个网络组件自己配
        HttpServer httpServer =new HttpServer();
        httpServer.start("localhost",8080);

    }
}
