package com.github.udoo.api;

import com.github.udoo.bo.AccountBO;

/**
 * @author dong.yang
 * @data 2019/8/13 16:18
 */
public interface AccountApi {

    /**
     * 根据主键获取账户信息
     *
     * @param id
     * @return
     */
    AccountBO getAccount(Integer id);
}
