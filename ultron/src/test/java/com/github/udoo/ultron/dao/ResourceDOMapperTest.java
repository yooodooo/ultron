package com.github.udoo.ultron.dao;

import com.github.udoo.ultron.dao.domain.ResourceDO;
import com.github.udoo.ultron.dao.mapper.primary.ResourceDOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dong.yang
 * @data 2019/7/13 18:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceDOMapperTest {

    @Autowired
    private ResourceDOMapper resourceDOMapper;

    @Test
    public void testInsert() {
        ResourceDO resourceDO = new ResourceDO();
        resourceDO.setName("aaaa");
        resourceDO.setPath("ssssssssssss");
        resourceDOMapper.insert(resourceDO);
    }
}
