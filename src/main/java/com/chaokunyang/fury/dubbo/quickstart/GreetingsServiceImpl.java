package com.chaokunyang.fury.dubbo.quickstart;

import com.chaokunyang.fury.dubbo.data.Foo;

/**
 * Test Service implementation.
 *
 * @author chaokunyang
 */
public class GreetingsServiceImpl implements GreetingsService {
  @Override
  public String sayHi(String name) {
    return "hi, " + name;
  }

  @Override
  public Foo sayFoo(Foo foo) {
    return Foo.create();
  }
}
