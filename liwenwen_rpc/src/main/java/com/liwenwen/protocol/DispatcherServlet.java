package com.liwenwen.protocol;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    //接收所有请求并处理请求
    @Override
    public void service(ServletRequest req, ServletResponse res) throws  IOException {
        // 每个handler 处理不同的请求,心跳请求，身份验证请求，通过调用不同的 handler为每个请求分配不同的处理逻辑
        try {
            new HttpServerHandler().handler(req,res);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
