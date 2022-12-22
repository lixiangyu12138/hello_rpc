package com.liwenwen.protocol;

import com.liwenwen.common.Invocation;
import com.liwenwen.register.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.management.ObjectName;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HttpServerHandler {
    //真正处理请求的方法
    public void handler(ServletRequest req, ServletResponse res) throws IOException, ClassNotFoundException {
        // 想要调用哪个接口的那哪个方法，参数是什么
        // 反序列化 读取对象
       Invocation invocation = (Invocation) new ObjectInputStream(req.getInputStream()).readObject();
        String interfaceName = invocation.getInterfaceName();  //根据接口
        // 如何调用相关的实现类   本地注册 去map中查找
        Class classImpl=LocalRegister.get(interfaceName);
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
