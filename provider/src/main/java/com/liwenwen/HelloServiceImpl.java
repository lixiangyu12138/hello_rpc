package com.liwenwen;

public class HelloServiceImpl implements HelloService {


    @Override
    public String sayHello(String name) {
        return "hello"+name;
    }
}
