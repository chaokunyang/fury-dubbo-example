package com.chaokunyang.fury.dubbo.quickstart;

import com.chaokunyang.fury.dubbo.data.Foo;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

/**
 * Test Client.
 *
 * @author chaokunyang
 */
public class Client {
  private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");

  public static void main(String[] args) {
    ReferenceConfig<GreetingsService> reference = new ReferenceConfig<>();
    reference.setApplication(new ApplicationConfig("first-dubbo-consumer"));
    reference.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":2181"));
    reference.setInterface(GreetingsService.class);
    GreetingsService service = reference.get();
    System.out.println("sayHi: " + service.sayHi("dubbo"));
    System.out.println("sayFoo: " + service.sayFoo(Foo.create()));
  }
}
