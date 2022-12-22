package com.liwenwen;

import com.liwenwen.common.Invocation;
import com.liwenwen.protocol.HttpClient;
import com.liwenwen.proxy.ProxyFactory;

import java.io.IOException;

public class Consumer {
    public static void main(String[] args) throws IOException {
        // 调用provider 中的 helloService 中的方法
         HelloService helloService= ProxyFactory.getProxy(HelloService.class);
         String result = helloService.sayHello("liwenwen");


//
//         String result =helloService.sayHello("liwenwen");
//         System.out.println(result);
        
        System.out.println(result);
    }
}
