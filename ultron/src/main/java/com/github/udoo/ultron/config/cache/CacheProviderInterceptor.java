package com.github.udoo.ultron.config.cache;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 * @author dong.yang
 * @data 2019/7/22 9:39
 */
@Aspect
@Configuration
public class CacheProviderInterceptor {

    @Around("")
    public Object interceptor() {
        return null;
    }
}
