package com.liwenwen.proxy;

import com.liwenwen.loadbalance.Loadbalance;
import com.liwenwen.common.Invocation;
import com.liwenwen.common.URL;
import com.liwenwen.protocol.HttpClient;
import com.liwenwen.register.MapRemoteRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyFactory {
    public static <T> T  getProxy(Class interfaceClass) {
        // 用户配置
        Object proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                HttpClient httpClient = new HttpClient();
                Invocation invocation  = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);
                //通过服务名称获取服务的ip地址和端口  服务发现
                List<URL> list  = MapRemoteRegister.get(interfaceClass.getName());
                // 负载均衡
                URL url = Loadbalance.random(list);
                // 服务调用
                String result = httpClient.send(url.getHostname(), url.getPort(), invocation);
                return result;

            }
        });
        return (T) proxyInstance;

    }
}
