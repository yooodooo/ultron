package com.github.udoo.ultron.doc.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author dong.yang
 * @date 2019/8/27 9:39
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "common")
public class ConfigLocation {

    /**
     * 测试配置文件加载路径
     * 主要位于4个位置
     * 1、-file:./config/
     */
    private String locationFileConfig;

    /**
     * 2、–file:./
     */
    private String locationFileClasspath;

    /**
     * 3、-classpath:/config/
     */
    private String locationClasspathConfig;

    /**
     * 4、-classpath:/
     */
    private String locationClasspath;
}
