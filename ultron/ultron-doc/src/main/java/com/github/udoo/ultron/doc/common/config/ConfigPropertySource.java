package com.github.udoo.ultron.doc.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author dong.yang
 * @date 2019/8/27 11:26
 */
@Data
@Configuration
@PropertySource(value = {"classpath:config/config1.properties", "classpath:config/config2.properties"})
@ConfigurationProperties(prefix = "config")
public class ConfigPropertySource {

    private String location;
}
