package com.github.udoo.ultron.doc.test.extension.api.impl;

import com.github.udoo.ultron.doc.test.extension.Log;
import com.github.udoo.ultron.doc.test.extension.api.SampleService;
import org.springframework.stereotype.Service;

/**
 * @author dong.yang
 * @date 2019/8/29 10:59
 */
@Service("sampleService")
public class SampleServiceImpl implements SampleService {

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
        System.out.println("hello sample service: " + msg);
    }

    @Override
    public void print() {
        System.out.println(injectValue);
    }
}
