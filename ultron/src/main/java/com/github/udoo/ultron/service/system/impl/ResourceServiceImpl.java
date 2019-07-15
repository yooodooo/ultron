package com.github.udoo.ultron.service.system.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.udoo.ultron.dao.domain.ResourceDO;
import com.github.udoo.ultron.dao.mapper.primary.ResourceDOMapper;
import com.github.udoo.ultron.model.vo.ResourceVO;
import com.github.udoo.ultron.service.system.ResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author dong.yang
 * @data 2019/7/13 19:28
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceDOMapper, ResourceDO> implements
        ResourceService {

    @Override
    public void saveOrUpdate(ResourceVO resourceVO) {
        ResourceDO resourceDO = new ResourceDO();
        BeanUtils.copyProperties(resourceVO, resourceDO);
        insertOrUpdate(resourceDO);
    }
}
