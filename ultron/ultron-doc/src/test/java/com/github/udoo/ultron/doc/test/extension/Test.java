package com.github.udoo.ultron.doc.test.extension;

import com.github.udoo.ultron.doc.test.extension.api.SampleService;
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

        System.out.println();
        SampleService sampleService = applicationContext.getBean("sampleService", SampleService.class);
        sampleService.hello("bbb");
        sampleService.print();
    }
}
