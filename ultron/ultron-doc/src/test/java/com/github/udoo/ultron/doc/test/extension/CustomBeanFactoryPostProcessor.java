package com.github.udoo.ultron.doc.test.extension;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
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
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryPostProcessor....");
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("sampleService");
        beanDefinition.getPropertyValues().add("injectValue", "test inject val");
    }
}
