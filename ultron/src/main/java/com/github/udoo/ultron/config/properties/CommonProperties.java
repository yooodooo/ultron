package com.github.udoo.ultron.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import java.util.List;
import java.util.Map;

/**
 * 一些通用的注入实例
 * 1、常规注入、表达式
 * 2、列表、哈希
 * 3、校验
 * 4、外部配置文件(默认从application.properties读取)
 *
 * @author dong.yang
 * @data 2019/7/17 17:33
 */
@Data
@Validated
@Configuration
@PropertySource(value = "classpath:common.properties")
@ConfigurationProperties(prefix = "common")
public class CommonProperties {

    private String name;

    private List<String> list;

    private Map<String, String> maps;

    /**
     * 验证不通过会抛出异常
     */
    @Max(value = 20)
    private Integer age;

    private String desc;

    private String randomValue;

    private Integer randomInt;
}
