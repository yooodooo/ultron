package com.github.udoo.ultron.config.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 通过prefix以及对象的方式进行注入
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
