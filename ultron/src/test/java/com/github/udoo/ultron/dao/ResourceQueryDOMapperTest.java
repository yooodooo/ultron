package com.github.udoo.ultron.dao;

import com.github.udoo.ultron.dao.domain.ResourceDO;
import com.github.udoo.ultron.dao.mapper.query.ResourceQueryDOMapper;
import org.junit.Assert;
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
public class ResourceQueryDOMapperTest {

    @Autowired
    private ResourceQueryDOMapper resourceQueryDOMapper;

    @Test
    public void testQuery() {
        ResourceDO resourceDO = resourceQueryDOMapper.selectById(1);
        Assert.assertNotNull(resourceDO);
    }
}
