package com.github.udoo.ultron.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 一些系统常量配置，注入的变量
 *
 * @author dong.yang
 * @data 2019/7/14 9:32
 */
@Data
@Component
@ConfigurationProperties(prefix = "ultron.system")
public class SystemProperties {

    /**
     * ultron.system.name
     */
    private String name;
    /**
     * ultron.system.count
     */
    private long count;
    private Host host;

    @Data
    public static class Host {
        /**
         * ultron.system.host.ip
         */
        private String ip;
        /**
         * ultron.system.host.port
         */
        private int port;
    }
}
