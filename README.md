# fury-dubbo-example
## Install [dubbo fury integration](https://github.com/fury-project/dubbo-serialization-fury)
```xml
<dependency>
  <groupId>org.furyio</groupId>
  <artifactId>dubbo-serialization-fury</artifactId>
  <version>0.1.2</version>
</dependency>
```

## Define Data
See https://github.com/chaokunyang/fury-dubbo-example/blob/main/src/main/java/com/chaokunyang/fury/dubbo/data/Foo.java
```java
public class Foo implements Serializable {
  private int f1;
  private List<Integer> list;
  private Map<String, Integer> map;

  public static Foo create() {
    Foo f = new Foo();
    f.f1 = 10;
    f.list = new ArrayList<>();
    f.map = new HashMap<>();
    f.list.add(1);
    f.list.add(2);
    f.map.put("k1", 100);
    f.map.put("k2", 200);
    return f;
  }
  
  // setter/getter/equals/hashCode/toString
}
```

## Define Service
```java
public interface HelloService {
  String sayHi(String name);

  Foo sayFoo(Foo foo);
}
```

## Implement Service
```java
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

```

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
See https://github.com/chaokunyang/fury-dubbo-example/blob/main/src/main/java/com/chaokunyang/fury/dubbo/quickstart/Server.java
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
See https://github.com/chaokunyang/fury-dubbo-example/blob/main/src/main/java/com/chaokunyang/fury/dubbo/quickstart/Client.java
```java
ReferenceConfig<GreetingsService> reference = new ReferenceConfig<>();
reference.setApplication(new ApplicationConfig("first-dubbo-consumer"));
reference.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":2181"));
reference.setInterface(GreetingsService.class);
GreetingsService service = reference.get();
System.out.println("sayHi: " + service.sayHi("dubbo"));
System.out.println("sayFoo: " + service.sayFoo(Foo.create()));
```
