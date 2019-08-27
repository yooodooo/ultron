package com.github.udoo.ultron.doc.common;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author dong.yang
 * @date 2019/8/27 13:46
 */
@Component
public class ApplicationRefreshEventTest implements ApplicationListener<ContextRefreshedEvent>, InitializingBean {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("ContextRefreshedEvent....");
        System.out.println(event.getApplicationContext().getDisplayName());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet...");
    }
}
