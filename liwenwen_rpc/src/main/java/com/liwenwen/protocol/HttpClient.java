package com.liwenwen.protocol;

import com.liwenwen.common.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpClient {
    /**
     * 发送请求方法
     * @param hostname 主机名
     * @param port 端口号
     * @param invocation 发送的参数
     * @return
     */
    public String send(String hostname, Integer port,Invocation invocation) throws IOException {
        // 用户配置
        URL url = new URL("http", hostname, port, "/");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);

        OutputStream outputStream = urlConnection.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        oos.writeObject(invocation);
        oos.flush();
        oos.close();
        InputStream inputStream = urlConnection.getInputStream();
        String result = IOUtils.toString(inputStream);
        return  result;


    }
}
