听说有人想在一篇文章中囊括SpringBoot所有配置信息，不管你信不信，反正我也只好选择原谅他了

说到配置文件,是我们程序员绕不开的结。我们可能见过不同的配置方式，如命令行、配置文件、配置中心等，每种方式都有特定的存在方式或使用场景。
对SpringBoot来说这些都是支持的方式，本文就试图来剖析SpringBoot中配置的使用做一个总结


1、配置文件

使用过SpringBoot的人都知道默认配置文件application.properties，基于键值对，位于resource跟目录下，名称也不能改，当然也可以用application.yml替代，两者
除了语法不同并没有本质的区别。

    application.properties
    name=config-sample
    count=102


在代码中使用

    @Value("${name}")
    private String name;
    
    @Value("${count}")
    private Integer count;

支持SpringEl表达式

    


当然在SpringBoot中推荐更优雅的方式，通过定义@ConfigurationProperties来

    @Data
    @Configuration
    @ConfigurationProperties
    public class CommonProperties {
    
        private String name;
        
        private Integer count;
    }

2、复杂对象
list
map
对象

3、验证
基于JSR303，需要加入@Validated


4、自定义文件
1、spring.profiles.active=dev
来指定文件，application-dev.properties

2、外部文件
@PropertySource(value = "classpath:common.properties")


5、配置中心


 



