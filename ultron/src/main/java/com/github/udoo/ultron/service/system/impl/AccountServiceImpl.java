package com.github.udoo.ultron.service.system.impl;

import com.github.udoo.ultron.common.util.CommonUtil;
import com.github.udoo.ultron.dao.domain.AccountDO;
import com.github.udoo.ultron.dao.mapper.primary.AccountDOMapper;
import com.github.udoo.ultron.dao.mapper.query.AccountQueryDOMapper;
import com.github.udoo.ultron.model.vo.AccountVO;
import com.github.udoo.ultron.service.system.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dong.yang
 * @data 2019/7/22 15:13
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDOMapper accountDOMapper;

    @Autowired
    AccountQueryDOMapper accountQueryDOMapper;

    @Override
    public AccountVO selectById(Long id) {
        AccountDO accountDO = accountQueryDOMapper.selectById(id);
        return CommonUtil.convert(accountDO, AccountVO.class);
    }

    @Override
    public void saveOrUpdate(AccountVO accountVO) {

    }
}
