package com.chaokunyang.fury.dubbo.quickstart;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.util.concurrent.CountDownLatch;

public class Server {
  private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");

  public static void main(String[] args) throws Exception {
    ServiceConfig<GreetingsService> service = new ServiceConfig<>();
    service.setApplication(new ApplicationConfig("first-dubbo-provider"));
    service.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":2181"));
    service.setInterface(GreetingsService.class);
    service.setRef(new GreetingsServiceImpl());
    service.setSerialization("fury");
    service.export();

    System.out.println("dubbo service started");
    new CountDownLatch(1).await();
  }
}