package com.github.udoo.ultron.doc.test.extension.api.impl;

import com.github.udoo.ultron.doc.test.extension.api.RegistService;

/**
 * @author dong.yang
 * @date 2019/8/29 18:20
 */
public class RegistServiceImpl implements RegistService {
    @Override
    public void hello() {
        System.out.println("hello RegisterService");
    }
}
