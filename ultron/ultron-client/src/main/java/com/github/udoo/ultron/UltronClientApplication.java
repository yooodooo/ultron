package com.github.udoo.ultron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author dong.yang
 * @data 2019/8/15 9:57
 */
@EnableAspectJAutoProxy
@SpringBootApplication
public class UltronClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(UltronClientApplication.class, args);
    }
}
