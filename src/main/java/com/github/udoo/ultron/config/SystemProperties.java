package com.github.udoo.ultron.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "ultron.system")
public class SystemProperties {

    private String name;
    private long count;
    private Host host;

    @Data
    public static class Host{
        private String ip;
        private int port;
    }
}
