package com.github.udoo.ultron.config.cache;

import com.github.udoo.ultron.config.cache.annotation.CacheProvider;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * @author dong.yang
 * @data 2019/7/22 9:39
 */
@Aspect
@Configuration
public class CacheProviderInterceptor {

    @Around("execution(* com.github.udoo.ultron.service..*(..)) && @annotation(com.github.udoo.ultron.config.cache.annotation.CacheProvider)")
    public Object interceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        //
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        CacheProvider cacheProvider = method.getDeclaredAnnotation(CacheProvider.class);
        if (cacheProvider != null) {
            System.out.println(cacheProvider.cache().getName());
        }
        return joinPoint.proceed();
    }
}
