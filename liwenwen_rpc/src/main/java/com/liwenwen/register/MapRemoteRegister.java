package com.liwenwen.register;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.liwenwen.common.FileIO;
import com.liwenwen.common.JsonUtils;
import com.liwenwen.common.URL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实现远程注册中心
 */
public class MapRemoteRegister {
    //为什么是list 因为provider 可能有多个副本
    private static Map<String, List<URL>> map = new HashMap<>();

    /**
     * 服务注册
     * @param interfaceName 接口名称
     * @param url  接口服务地址
     */
    public static void regist(String interfaceName,URL url){
        List<URL> list  = map.get(interfaceName);
        // 服务未注册
        if(list== null){
            list = new ArrayList<>();
        }
        list.add(url);
        map.put(interfaceName,list);
        FileIO.write(map);

    }

    /**
     * 服务发现
     * @param interfaceName 接口名称
     * @return 服务地址列表
     */
    public static List<URL> get(String interfaceName) {
        JsonObject jsonObject = JsonUtils.parseJson(FileIO.read());
        Gson gson = new Gson();
        Map<String, List<URL>> map = gson.fromJson(jsonObject, new TypeToken<Map<String, List<URL>>>() {}.getType());
        // 可以看到上面的代码使用了TypeToken，它是gson提供的数据类型转换器，可以支持各种数据集合类型转换。
        List<URL> list =  map.get(interfaceName);
        // System.out.println(list.get(0).toString());
        return list;

    }
}
