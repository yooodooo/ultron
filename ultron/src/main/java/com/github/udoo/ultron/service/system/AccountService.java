package com.github.udoo.ultron.service.system;

import com.github.udoo.ultron.model.vo.AccountVO;

/**
 * @author dong.yang
 * @data 2019/7/22 15:09
 */
public interface AccountService {

    AccountVO selectById(Long id);

    void saveOrUpdate(AccountVO accountVO);
}
