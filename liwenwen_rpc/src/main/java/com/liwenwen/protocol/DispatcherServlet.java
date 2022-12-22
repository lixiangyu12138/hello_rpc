package com.liwenwen.protocol;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    //接收所有请求并处理请求
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        //每个handler 处理不同的请求
        try {
            new HttpServerHandler().handler(req,res);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
