package com.chaokunyang.fury.dubbo.example1;

import com.chaokunyang.fury.dubbo.data.Foo;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

/**
 * Test Client.
 *
 * @author chaokunyang
 */
public class Client {
  public static void main(String[] args) {
    ReferenceConfig<HelloService> referenceConfig = new ReferenceConfig<>();
    referenceConfig.setInterface(HelloService.class);
    referenceConfig.setTimeout(1000000);

    ProtocolConfig protocolConfig = new ProtocolConfig(CommonConstants.DUBBO, 10881);
    protocolConfig.setSerialization("fury");
    DubboBootstrap.getInstance()
        .reference(referenceConfig)
        .application(new ApplicationConfig("consumer"))
        .registry(new RegistryConfig("zookeeper://127.0.0.1:2181"))
        .protocol(protocolConfig)
        .start(true);

    System.out.println("sayHi: " + referenceConfig.get().sayHi("venus"));
    System.out.println("sayHi: " + referenceConfig.get().sayFoo(Foo.create()));
  }
}
