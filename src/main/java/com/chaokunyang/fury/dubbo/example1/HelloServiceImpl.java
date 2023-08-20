package com.chaokunyang.fury.dubbo.example1;

import com.chaokunyang.fury.dubbo.data.Foo;

/**
 * Test Service implementation.
 *
 * @author chaokunyang
 */
public class HelloServiceImpl implements HelloService {
  @Override
  public String sayHi(String name) {
    return "hi, " + name;
  }

  @Override
  public Foo sayFoo(Foo foo) {
    return Foo.create();
  }
}
