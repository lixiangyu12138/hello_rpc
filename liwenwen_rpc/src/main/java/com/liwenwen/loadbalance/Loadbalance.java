package com.liwenwen.loadbalance;

import com.liwenwen.common.URL;

import java.util.List;
import java.util.Random;

public class Loadbalance {
    /**
     * 随机算法 输入一个服务地址列表，随机得到一个服务地址
     * @param urls 服务地址列表
     * @return 服务地址
     */
    public static URL random(List<URL> urls){
        // 模拟负载均衡算法
        Random random = new Random();
        int i = random.nextInt(urls.size());
        return urls.get(i);
    }
}
