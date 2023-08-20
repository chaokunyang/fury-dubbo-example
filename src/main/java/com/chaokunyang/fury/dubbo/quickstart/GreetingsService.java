package com.chaokunyang.fury.dubbo.quickstart;

import com.chaokunyang.fury.dubbo.data.Foo;

/**
 * Test service interface.
 *
 * @author chaokunyang
 */
public interface GreetingsService {
  String sayHi(String name);

  Foo sayFoo(Foo foo);
}
