package com.github.udoo.ultron.doc.test.extension.api.impl;

import com.github.udoo.ultron.doc.test.extension.api.TestService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @author dong.yang
 * @date 2019/8/29 10:35
 */
@Service(value = "testService")
public class TestServiceImpl implements TestService, BeanNameAware, BeanFactoryAware, ApplicationContextAware {

    private String beanName;

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void hello(String message) {
        System.out.println("hello testService: " + message);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("TestService BeanFactoryAware: " + beanFactory.getClass().getName());
        if (beanFactory instanceof ConfigurableListableBeanFactory) {
            this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
        }
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("TestService BeanNameAware：" + name);
        this.beanName = name;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("TestService ApplicationContextAware: " + applicationContext.getDisplayName());
    }
}
