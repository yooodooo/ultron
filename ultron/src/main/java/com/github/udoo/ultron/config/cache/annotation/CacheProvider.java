package com.github.udoo.ultron.config.cache.annotation;

import java.lang.annotation.*;

/**
 * @author dong.yang
 * @data 2019/7/22 9:39
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheProvider {

    /**
     * 指定cache的名称，使用cache对象类名
     *
     * @return
     */
    Class<?> cache();

    /**
     * 获取cache的field,保持唯一性的字段
     *
     * @return
     */
    String cacheField() default "cache";
}
