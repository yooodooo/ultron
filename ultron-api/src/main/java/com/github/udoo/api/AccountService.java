package com.github.udoo.api;

import com.github.udoo.dto.AccountDTO;

/**
 * @author dong.yang
 * @data 2019/8/13 16:18
 */
public interface AccountService {

    AccountDTO getAccount(Integer id);
}
