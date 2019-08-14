package com.github.udoo.ultron.service.system;

import com.baomidou.mybatisplus.service.IService;
import com.github.udoo.ultron.dao.domain.ResourceDO;
import com.github.udoo.ultron.model.vo.ResourceVO;

/**
 * @author dong.yang
 * @data 2019/7/15 13:46
 */
public interface ResourceQueryService extends IService<ResourceDO> {

    ResourceVO queryById(Long id);
}
