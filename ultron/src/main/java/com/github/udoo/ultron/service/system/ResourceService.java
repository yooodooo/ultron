package com.github.udoo.ultron.service.system;

import com.baomidou.mybatisplus.service.IService;
import com.github.udoo.ultron.dao.domain.ResourceDO;
import com.github.udoo.ultron.model.vo.ResourceVO;

/**
 * @author dong.yang
 * @data 2019/7/13 18:40
 */
public interface ResourceService extends IService<ResourceDO> {

    void saveOrUpdate(ResourceVO resourceVO);
}
