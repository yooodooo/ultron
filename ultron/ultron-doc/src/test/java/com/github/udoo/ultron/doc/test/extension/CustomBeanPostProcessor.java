package com.github.udoo.ultron.doc.test.extension;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * @author dong.yang
 * @date 2019/8/29 10:48
 */
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor, Ordered {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println();
        System.out.println("[BeanPostProcessor.postProcessBeforeInitialization]" + beanName + "...");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[BeanPostProcessor.postProcessAfterInitialization]" + beanName + "...");
        String packageName = bean.getClass().getPackage().getName();
        if (packageName.startsWith("com.github.udoo")) {
            return Proxy.newProxyInstance(getClass().getClassLoader(), bean.getClass().getInterfaces(), new CustomBeanPostProcessorProxy(bean));
        }
        return bean;
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }
}
