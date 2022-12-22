package com.liwenwen.register;

import java.util.HashMap;
import java.util.Map;

public class LocalRegister {
    private static Map<String, Class> map = new HashMap<>();

    /**
     *  注册将接口的名字，接口的类型存到map里
     * @param interfaceName
     * @param implClass
     */
    public static void regist(String interfaceName,Class implClass){
        map.put(interfaceName,implClass);

    }
    public static Class get(String interfaceName){
        return map.get(interfaceName);
    }
}
