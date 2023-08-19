package com.chaokunyang.fury.dubbo.quickstart;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

public class Client {
  private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");

  public static void main(String[] args) {
    ReferenceConfig<GreetingsService> reference = new ReferenceConfig<>();
    reference.setApplication(new ApplicationConfig("first-dubbo-consumer"));
    reference.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":2181"));
    reference.setInterface(GreetingsService.class);
    GreetingsService service = reference.get();
    String message = service.sayHi("dubbo");
    System.out.println("sayHi: " + message);
  }
}