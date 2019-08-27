package com.github.udoo.ultron.doc.test.common;

import com.github.udoo.ultron.doc.common.config.ConfigLocation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dong.yang
 * @date 2019/8/27 9:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigLocationTest {

    @Autowired
    private ConfigLocation configLocation;

    @Test
    public void locationTest() {
        System.out.println(configLocation);
    }
}
