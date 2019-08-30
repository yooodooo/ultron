[./pic/bean.jpg]

BeanFactoryPostProcessor

可以自定义修改容器中Bean的定义，比如修改bean的一些属性。需要注意的是此时Bean未初始化，不能进行Bean的操作

```
public interface BeanFactoryPostProcessor {

	void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
```
以及一个继承他的接口BeanDefinitionRegistryPostProcessor，提供了注册BeanDefinition的能力
```
public interface BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor {

	void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException;
}
```


使用场景：

- PropertyPlaceholderConfigurer 

我们知道Bean中使用外部配置时一般通过${}占位操作，PropertyPlaceholderConfigurer将容器
中所有BeanDefinition中${...}用给定的属性替换。同样可以扩展到配置项加解密、远程获取替换等操作，都可以在这个阶段进行

- MapperScannerConfigurer

我们看一个Spring集成mybatis时配置mapper的用法
```
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <property name="basePackage" value="com.x.y.z"/>
    <property name="sqlSessionFactoryBeanName" value="sqlSessionFactoryBean"/>
</bean>
```
这里并没有配置每个mapper，此时spring容器是没有相应的BeanDefinition，而MapperScannerConfigurer
的做法是扫描basePackage下的类生成相应BeanDefinition，并通过BeanDefinitionRegistry进行注册

例子

下面的例子展示了两种用法：
1、对bean的属性注入值
2、手动注入BeanDefinition

```
@Component
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor, BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //获取到sampleService并对其属性injectValue注入相应的值
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("sampleService");
        beanDefinition.getPropertyValues()
                .add("injectValue", "test inject val");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //手动注入服务，此时RegisterServiceImpl中并没有使用@Service,注册后可以注入使用
        //@Autowired
        //private RegisterService registerService;
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(RegisterServiceImpl.class);
        registry.registerBeanDefinition("registerService", genericBeanDefinition);
    }
}
```

Aware

在Spring中使用Bean一般不需要关注容器相关的数据，但是如果确实有需要也可以通过Aware来获取响应的配置。下面展示了一些常见的Aware接口及其获取到容器的数据
```
BeanNameAware	获得当前Bean的名称
BeanFactoryAware	获得beanfactory
ApplicationContextAware	获得ApplicationContext
MessageSourceAware	获得MessageSource
ApplicationEventPublisherAware	获得ApplicationEventPublisher，可以发布事件
ResourceLoaderAware	获得ResourceLoader
```

BeanPostProcessor

提供了在Bean初始化过程中修改Bean实例的能力，提供初始化前和初始化后操作。
```
public interface BeanPostProcessor {

	@Nullable
	default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Nullable
	default Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

}
```
主要使用场景实现AOP自动代理，在Spring框架中一般使用子类InstantiationAwareBeanPostProcessor,提供了更细粒度的支持。
可以参考AbstractAutoProxyCreator如下方法
```
@Override
public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
    Object cacheKey = getCacheKey(beanClass, beanName);

    if (!StringUtils.hasLength(beanName) || !this.targetSourcedBeans.contains(beanName)) {
        if (this.advisedBeans.containsKey(cacheKey)) {
            return null;
        }
        if (isInfrastructureClass(beanClass) || shouldSkip(beanClass, beanName)) {
            this.advisedBeans.put(cacheKey, Boolean.FALSE);
            return null;
        }
    }

    // Create proxy here if we have a custom TargetSource.
    // Suppresses unnecessary default instantiation of the target bean:
    // The TargetSource will handle target instances in a custom fashion.
    TargetSource targetSource = getCustomTargetSource(beanClass, beanName);
    if (targetSource != null) {
        if (StringUtils.hasLength(beanName)) {
            this.targetSourcedBeans.add(beanName);
        }
        Object[] specificInterceptors = getAdvicesAndAdvisorsForBean(beanClass, beanName, targetSource);
        Object proxy = createProxy(beanClass, beanName, specificInterceptors, targetSource);
        this.proxyTypes.put(cacheKey, proxy.getClass());
        return proxy;
    }

    return null;
}
```

例子

对服务中使用了@Log的方法记录耗时
```
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor, Ordered {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        String packageName = bean.getClass().getPackage().getName();
        if (packageName.startsWith("com.github.sample")) {
            return Proxy.newProxyInstance(getClass().getClassLoader(), bean.getClass().getInterfaces(), new CustomBeanPostProcessorProxy(bean));
        }
        return bean;
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }
}
```
代理简单实现，基于JDK接口代理
```
public class CustomBeanPostProcessorProxy implements InvocationHandler {

    private Object target;

    public CustomBeanPostProcessorProxy(Object object) {
        this.target = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long begin = System.currentTimeMillis();
        Object result = method.invoke(target, args);
        if (shouldProxy(method)) {
            System.out.println("target: " + target.getClass().getName() + "." + method.getName() + " cost: " + (System.currentTimeMillis() - begin)+" ms");
        }
        return result;
    }

    private boolean shouldProxy(Method proxyMethod) throws Exception {
        Method targetMethod = target.getClass().getMethod(proxyMethod.getName(), proxyMethod.getParameterTypes());
        if (targetMethod != null) {
            return targetMethod.getAnnotation(Log.class) != null;
        }
        return false;
    }
}
```


