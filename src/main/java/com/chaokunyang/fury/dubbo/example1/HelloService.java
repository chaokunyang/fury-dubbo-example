package com.chaokunyang.fury.dubbo.example1;

import com.chaokunyang.fury.dubbo.data.Foo;

/**
 * Test service interface.
 *
 * @author chaokunyang
 */
public interface HelloService {
  String sayHi(String name);

  Foo sayFoo(Foo foo);
}
