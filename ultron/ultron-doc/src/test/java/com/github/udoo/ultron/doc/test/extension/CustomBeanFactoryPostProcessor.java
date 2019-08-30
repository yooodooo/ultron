package com.github.udoo.ultron.doc.test.extension;

import com.github.udoo.ultron.doc.test.extension.api.impl.RegisterServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * 使用场景：在bean初始化之前接入BeanDefinition
 * <p>
 * PropertyPlaceholderConfigurer 将容器中所有BeanDefinition中${...}用给定的属性替换,同样如果配置文件加密、远程获取替换都可以在此扩展
 * <p>
 * MapperScannerConfigurer
 *
 * @author dong.yang
 * @date 2019/8/29 11:05
 */
@Component
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor, BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryPostProcessor.postProcessBeanFactory...");
        //获取到sampleService并对其属性injectValue注入相应的值
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("sampleService");
        beanDefinition.getPropertyValues()
                .add("injectValue", "test inject val");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("BeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry...");
        //手动注入服务，此时RegisterServiceImpl中并没有使用@Service,注册后可以注入使用
        //@Autowired
        //private RegisterService registerService;
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(RegisterServiceImpl.class);
        genericBeanDefinition.setInitMethodName("helloAgain");
        registry.registerBeanDefinition("registerService", genericBeanDefinition);
    }
}
