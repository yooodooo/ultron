package com.github.udoo.ultron.doc.test.extension.api.impl;

import com.github.udoo.ultron.doc.test.extension.api.TestService;
import org.springframework.stereotype.Service;

/**
 * @author dong.yang
 * @date 2019/8/29 10:35
 */
@Service(value = "testService")
public class TestServiceImpl implements TestService {

    @Override
    public void hello(String message) {
        System.out.println("hello testService: " + message);
    }
}
