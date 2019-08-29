

BeanFactoryPostProcessor

在bean初始化之前介入BeanDefinition
```
public interface BeanFactoryPostProcessor {

	void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
```
可以自定义修改容器中Bean的定义，比如修改bean的一些属性。需要注意的是此时Bean未初始化，不能进行Bean的操作

使用场景：

- PropertyPlaceholderConfigurer 

我们知道使用外部配置文件时一般通过${}的方式使用

将容器中所有BeanDefinition中${...}用给定的属性替换,同样如果配置文件加密、远程获取替换都可以在此扩展

- MapperScannerConfigurer

在Spring集成mybatis时并不会配置每个mapper，所以容器中并没有这些BeanDefinition。而MapperScannerConfigurer
的做法是扫描basePackage下的类生成相应BeanDefinition，并通过BeanDefinitionRegistry进行注册


BeanPostProcessor

AOP自动代理


