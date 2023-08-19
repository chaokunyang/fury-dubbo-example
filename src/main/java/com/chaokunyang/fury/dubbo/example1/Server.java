package com.chaokunyang.fury.dubbo.example1;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.rpc.Constants;

public class Server {
  public static void main(String[] args) throws Exception {
    ServiceConfig<HelloService> sc = new ServiceConfig<>();
    sc.setInterface(HelloService.class);
    sc.setTimeout(1000000); // 5, 595, 622
    System.setProperty(Constants.SERIALIZATION_SECURITY_CHECK_KEY, "false");
    sc.setRef(new HelloServiceImpl());
    RegistryConfig rc = new RegistryConfig("zookeeper://127.0.0.1:2181");
    ProtocolConfig protocolConfig = new ProtocolConfig(CommonConstants.DUBBO, 10880);
    protocolConfig.setSerialization("fastjson2");
    DubboBootstrap.getInstance()
      .application(new ApplicationConfig("provider-demo"))
      .service(sc)
      .registry(rc)
      .protocol(protocolConfig)
      .start()
      .await();
  }
}