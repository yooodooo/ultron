package com.github.udoo.ultron.service.system.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.udoo.ultron.dao.domain.ResourceDO;
import com.github.udoo.ultron.dao.mapper.query.ResourceQueryDOMapper;
import com.github.udoo.ultron.model.vo.ResourceVO;
import com.github.udoo.ultron.service.system.ResourceQueryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author dong.yang
 * @data 2019/7/15 13:47
 */
@Service
public class ResourceQueryServiceImpl extends ServiceImpl<ResourceQueryDOMapper, ResourceDO> implements
        ResourceQueryService {

    @Override
    public ResourceVO queryById(Long id) {
        ResourceDO resourceDO = selectById(id);
        if (resourceDO != null) {
            ResourceVO resourceVO = new ResourceVO();
            BeanUtils.copyProperties(resourceDO, resourceVO);
            return resourceVO;
        }
        return null;
    }
}
