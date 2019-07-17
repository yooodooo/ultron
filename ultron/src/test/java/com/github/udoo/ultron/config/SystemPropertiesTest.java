package com.github.udoo.ultron.config;


import com.github.udoo.ultron.config.properties.SystemProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemPropertiesTest {

    @Autowired
    private SystemProperties systemProperties;

    @Value("${ultron.system.other}")
    private String other;

    /**
     * 支持SpEL表达式
     */
    @Value("#{'${ultron.system.keys}'.split(',')}")
    private String[] keys;

    /**
     * 如果没有配置项ultron.system.title,使用默认值
     */
    @Value("${ultron.system.title:this is a title}")
    private String title;


    @Test
    public void testSystemProperties() {
        Assert.assertEquals(10, systemProperties.getCount());
        Assert.assertEquals("hello spring boot", systemProperties.getName());
    }

    @Test
    public void testSystemNestedProperties() {
        Assert.assertEquals("127.0.0.1", systemProperties.getHost().getIp());
        Assert.assertEquals(8080, systemProperties.getHost().getPort());
    }

    @Test
    public void testOtherProperties() {
        Assert.assertEquals("123", other);
        Assert.assertEquals(2, keys.length);
        Assert.assertEquals("this is a title", title);
    }
}
