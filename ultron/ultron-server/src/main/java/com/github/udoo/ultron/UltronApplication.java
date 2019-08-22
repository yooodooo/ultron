package com.github.udoo.ultron;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAspectJAutoProxy
@EnableTransactionManagement
@SpringBootApplication
@NacosPropertySource(dataId = "ultron", groupId = "ultron.server.group", autoRefreshed = true)
public class UltronApplication {

    public static void main(String[] args) {
        SpringApplication.run(UltronApplication.class, args);
    }

}
