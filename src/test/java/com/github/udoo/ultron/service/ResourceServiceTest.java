package com.github.udoo.ultron.service;

import com.github.udoo.ultron.model.vo.ResourceVO;
import com.github.udoo.ultron.service.system.ResourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dong.yang
 * @data 2019/7/13 19:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceServiceTest {

    @Autowired
    ResourceService resourceService;

    @Test
    public void testInsert() {
        ResourceVO resourceVO = new ResourceVO();
        resourceVO.setName("ssssssssssss");
        resourceVO.setPath("vvvvvvvvv");
        resourceService.saveOrUpdate(resourceVO);
    }
}
