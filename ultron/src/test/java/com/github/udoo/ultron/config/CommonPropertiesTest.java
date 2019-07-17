package com.github.udoo.ultron.config;

import com.github.udoo.ultron.config.properties.CommonProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author dong.yang
 * @data 2019/7/17 17:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonPropertiesTest {

    @Autowired
    private CommonProperties commonProperties;

    @Test
    public void testCommon() {
        assertEquals("ultron", commonProperties.getName());
        assertEquals(3, commonProperties.getList().size());
        assertEquals("a", commonProperties.getMaps().get("k1"));
    }


}
