听说有人想在一篇文章中囊括SpringBoot所有配置信息，不管你信不信，反正我也只好选择原谅他了

说到配置文件,是我们程序员绕不开的结。我们可能见过不同的配置方式，如命令行、配置文件、配置中心等，每种方式都有特定的存在方式或使用场景。
对SpringBoot来说这些都是支持的方式，本文就试图来剖析SpringBoot中配置的使用做一个总结


一、配置文件

使用过SpringBoot的人都知道默认配置文件application.properties。基于键值对，位于classpath下，名称也不能改，当然也可以用application.yml替代，两者
除了语法不同并没有本质的区别。下面是一个基础的配置项
application.properties
```
name=config-sample
count=102
booleanVal=true
keys=1,2,3
```
在代码中可通过如下两种方式来获取值

**@Value**  
使用过Spring的人都知道该如何使用
```
@Value("${name}")
private String name;

@Value("${count}")
private Integer count;

@Value("${booleanVal}")
private boolean booleanVal;
```
使用Value也支持SpringEl表达式和默认值
```
@Value("#{'${keys}'.split(',')}")
private String[] keys;

//静态方法调用
@Value("#{T(Integer).parseInt('${count}') > 100}")
private boolean booleanVal;

@Value("${title:this is a title}")
private String title;
```

**@ConfigurationProperties**  
使用ConfigurationProperties时一般定义一个类来接收
```
@Data
@Configuration
@ConfigurationProperties
public class CommonProperties {

    private String name;
    
    private Integer count;
    
    private boolean booleanVal;
}
```

其实application.properties文件的路径不仅是上面说的classpath下，按照优先级会从如下四个路径寻找

- file:./config/
- file:./
- classpath:/config/
- classpath:/

二、复杂对象

下面的例子都基于 **@ConfigurationProperties** 
配置文件common.properties展示了一些配置方式
```
common.name=sample

##校验不能大于20
common.age=15

##注入集合
common.list[0]=a
common.list[1]=b
common.list[2]=c

##注入哈希
common.maps.k1=a
common.maps.k2=b
        
#占位符
common.desc=my name is ${common.name}

#内置：random 中划线对应驼峰命名
common.random-value=${random.value}
common.random-int=${random.int}
```
通过定义CommonProperties类来接收配置项
```
@Data
@Validated
@Configuration
@PropertySource(value = "classpath:common.properties")
@ConfigurationProperties(prefix = "common")
public class CommonProperties {

    private String name;

    private List<String> list;

    private Map<String, String> maps;

    //验证不通过会抛出异常
    @Max(value = 20)
    private Integer age;

    private String desc;

    private String randomValue;

    private Integer randomInt;
}
```   
接下来对配置文件中涉及的配置要点进行说明

1、配置文件common.properties

前面说了SpringBoot的默认配置文件是application.properties，而这里通过@PropertySource来指定了外部文件。
```
@PropertySource(value = {"classpath:config/config1.properties", "classpath:config/config2.properties"})
```
这里引入了多个外部文件，加载顺序从左至右，存在相同的配置项会被后面的覆盖
还有一种方式，在application.properties中通过设置profile来指定
```
spring.profiles.active=dev
```
那么配置文件application-dev.properties也将被加载,通常这种方式来指定运行的环境

2、前缀 

可以通过prefix来指定配置项前缀，达到一个分组的目的，
在SpringBoot中很多starter都采用这种方式来进行配置项的隔离。这里通过common前缀分隔开来。

3、注入list与hash
```
common.list[0]=a
common.list[1]=b
common.list[2]=c
```    
其中list对应实体中集合的名称，下标从0开始

4、占位符与内置对象

配置文件中也可通过``${}``引用其他的配置项，进行占位操作。这里也展示了内置对象random的使用

5、验证

基于JSR303，需要类上加入@Validated，需要引入maven
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```
如果验证不通过，在加载阶段将抛出异常

三、自定义对象

我们在使用SpringBoot的一些starter的时候，经常会碰见类似这样的配置
```
spring.redis.lettuce.pool.max-active=8
spring.redis.lettuce.pool.max-wait=-1
spring.redis.lettuce.pool.max-idle=8
spring.redis.lettuce.pool.min-idle=0
```   
这里描述了使用redis客户端lettuce配置连接池的一些属性，这里就涉及到自定义对象的配置方式，可以在
RedisProperties中进行查看
```
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {
    
    //省略其他信息
    
    private final Lettuce lettuce = new Lettuce();
    
    public static class Lettuce {
        
        private Pool pool;
        
        //省略其他信息
    }

}
```   
其中pool也是一个对象
```
public static class Pool {
    private int maxIdle = 8;

    private int minIdle = 0;

    private int maxActive = 8;

    private Duration maxWait = Duration.ofMillis(-1);

    private Duration timeBetweenEvictionRuns;
    
    //省略其他信息
}
```
这里通过自定义对象将各种配置项隔离开来，语义清晰，使用简便不易混淆

四、配置中心

随着微服务的发展，服务数量激增，同时配置项也可能在多个系统中共用，配置中心也便应运而生。
基于SpringBoot的配置和注册中心也很多，如SpringCloudConfig,Eureka,Applo,Nacos等。
这里基于Nacos来举例说明

1、引入maven依赖
```
<dependency>
    <groupId>com.alibaba.boot</groupId>
    <artifactId>nacos-config-spring-boot-starter</artifactId>
</dependency>
 ```   
2、配置文件中指定配置中心
```
#nacos配置中心
nacos.config.server-addr=127.0.0.1:8848
 ```   
3、使用

Application.java中加入
```
@NacosPropertySource(dataId = "sample", groupId = "sample.group", autoRefreshed = true)
``` 
使用@NacosValue获取
```
@NacosValue(value = "${nacos.version:0}", autoRefreshed = true)
private String nacosVersion;
```

写到这里本文也差不多结束了，本文主要对SpringBoot中涉及配置项进行了总结，包括配置文件、配置项中各种数据结构的支持，同时也引入了
配置中心Nacos的简单使用用例