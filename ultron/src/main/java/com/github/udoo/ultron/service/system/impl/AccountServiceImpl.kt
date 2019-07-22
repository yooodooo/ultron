package com.github.udoo.ultron.service.system.impl

import com.github.udoo.ultron.common.util.CommonUtil
import com.github.udoo.ultron.dao.domain.AccountDO
import com.github.udoo.ultron.dao.mapper.primary.AccountDOMapper
import com.github.udoo.ultron.dao.mapper.query.AccountQueryDOMapper
import com.github.udoo.ultron.model.vo.AccountVO
import com.github.udoo.ultron.service.system.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl:AccountService {

    @Autowired
    lateinit var accountDOMapper: AccountDOMapper

    @Autowired
    lateinit var accountQueryDOMapper: AccountQueryDOMapper

    override fun selectById(id: Long?): AccountVO {
        val accountDO = accountQueryDOMapper.selectById(id)
        return CommonUtil.convert(accountDO, AccountVO::class.java)
    }

    override fun saveOrUpdate(accountVO: AccountVO) {
        val accountDO = CommonUtil.convert(accountVO, AccountDO::class.java)
    }
}