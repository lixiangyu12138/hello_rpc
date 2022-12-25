package com.liwenwen.protocol;

import com.liwenwen.common.Invocation;
import com.liwenwen.register.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HttpServerHandler {
    //真正处理请求的方法
    public void handler(ServletRequest req, ServletResponse res) throws IOException, ClassNotFoundException {
        // Invocation 想要调用哪个接口的那哪个方法，参数是什么
        // 消费者将要调用的接口，方法，参数等信息保存到Invocation对象中，通过http 请求的方式传递到这里，进行处理
        // 反序列化 读取对象 从request 中获取invocation 对象
       Invocation invocation = (Invocation) new ObjectInputStream(req.getInputStream()).readObject();
       // 从invocation对象中获取接口名
        String interfaceName = invocation.getInterfaceName();  //
        // 如何调用相关的实现类   本地注册 去map中查找
        Class classImpl=LocalRegister.get(interfaceName);  //根据接口名称找到实现类
        try {
            Method method =classImpl.getMethod(invocation.getMethodName(),invocation.getParameterTypes());
            String result =(String) method.invoke(classImpl.newInstance(),invocation.getParameters());
            // 将结果写到 Response 中
            IOUtils.write(result, res.getOutputStream());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


    }
}
