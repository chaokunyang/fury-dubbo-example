# fury-dubbo-example
## Steps to run
### Start zookeeper
```bash
wget https://www.apache.org/dyn/closer.lua/zookeeper/zookeeper-3.9.0/apache-zookeeper-3.9.0-bin.tar.gz
tar -zxf apache-zookeeper-3.9.0-bin.tar.gz
cd apache-zookeeper-3.9.0-bin
cat >conf/zoo.cfg <<EOL
tickTime=2000
dataDir=data
clientPort=2181
EOL
bin/zkServer.sh start
```

### Start server
```java
ServiceConfig<GreetingsService> service = new ServiceConfig<>();
service.setApplication(new ApplicationConfig("first-dubbo-provider"));
service.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":2181"));
service.setInterface(GreetingsService.class);
service.setRef(new GreetingsServiceImpl());
service.setSerialization("fury");
service.export();
System.out.println("dubbo service started");
new CountDownLatch(1).await();
```

### Start Client
```java
ReferenceConfig<GreetingsService> reference = new ReferenceConfig<>();
reference.setApplication(new ApplicationConfig("first-dubbo-consumer"));
reference.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":2181"));
reference.setInterface(GreetingsService.class);
GreetingsService service = reference.get();
System.out.println("sayHi: " + service.sayHi("dubbo"));
System.out.println("sayFoo: " + service.sayFoo(Foo.create()));
```