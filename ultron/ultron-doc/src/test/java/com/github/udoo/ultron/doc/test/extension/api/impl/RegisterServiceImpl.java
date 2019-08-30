package com.github.udoo.ultron.doc.test.extension.api.impl;

import com.github.udoo.ultron.doc.test.extension.api.RegisterService;
import com.github.udoo.ultron.doc.test.extension.api.TestService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dong.yang
 * @date 2019/8/29 18:20
 */
public class RegisterServiceImpl implements RegisterService, InitializingBean {

    @Autowired
    TestService testService;

    @Override
    public void hello() {
        System.out.println("hello RegisterService");
    }

    @Override
    public void helloAgain() {
        System.out.println("RegisterService init-method ...");
        testService.hello("RegisterService");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("RegisterServiceImpl InitializingBean....");
    }


}
