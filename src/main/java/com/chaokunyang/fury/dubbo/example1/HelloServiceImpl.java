package com.chaokunyang.fury.dubbo.example1;

public class HelloServiceImpl implements HelloService {
  @Override
  public String sayHi(String name) {
    return "hi, " + name;
  }
}