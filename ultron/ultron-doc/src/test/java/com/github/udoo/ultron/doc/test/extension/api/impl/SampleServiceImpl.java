package com.github.udoo.ultron.doc.test.extension.api.impl;

import com.github.udoo.ultron.doc.test.extension.common.Log;
import com.github.udoo.ultron.doc.test.extension.api.RegisterService;
import com.github.udoo.ultron.doc.test.extension.api.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dong.yang
 * @date 2019/8/29 10:59
 */
@Service("sampleService")
public class SampleServiceImpl implements SampleService {

    @Autowired
    private RegisterService registerService;

    private String injectValue;

    public String getInjectValue() {
        return injectValue;
    }

    public void setInjectValue(String injectValue) {
        this.injectValue = injectValue;
    }

    @Override
    @Log
    public void hello(String msg) {
        System.out.println("Hello SampleService: " + msg);
        registerService.hello();
    }

    @Override
    public void print() {
        System.out.println(injectValue);
    }
}
