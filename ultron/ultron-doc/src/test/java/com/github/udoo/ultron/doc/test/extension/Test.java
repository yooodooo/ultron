package com.github.udoo.ultron.doc.test.extension;

import com.github.udoo.ultron.doc.test.extension.api.RegistService;
import com.github.udoo.ultron.doc.test.extension.api.SampleService;
import com.github.udoo.ultron.doc.test.extension.api.TestService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author dong.yang
 * @date 2019/8/29 10:34
 */
public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("com.github.udoo.ultron.doc.test");
        applicationContext.refresh();

        TestService testService = applicationContext.getBean("testService", TestService.class);
        testService.hello("aa");

        SampleService sampleService = applicationContext.getBean("sampleService", SampleService.class);
        sampleService.hello("bbb");
        sampleService.print();

        RegistService registService = applicationContext.getBean("registService", RegistService.class);
        registService.hello();
    }
}
