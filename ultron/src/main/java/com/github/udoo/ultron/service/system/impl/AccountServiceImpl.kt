package com.github.udoo.ultron.service.system.impl

import com.github.udoo.ultron.common.util.CommonUtil
import com.github.udoo.ultron.dao.domain.AccountDO
import com.github.udoo.ultron.dao.mapper.primary.AccountDOMapper
import com.github.udoo.ultron.dao.mapper.query.AccountQueryDOMapper
import com.github.udoo.ultron.model.vo.AccountVO
import com.github.udoo.ultron.service.system.AccountService
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl(private val accountDOMapper: AccountDOMapper,
                         private val accountQueryDOMapper: AccountQueryDOMapper) : AccountService {

    override fun selectById(id: Int?): AccountVO {
        val accountDO = accountQueryDOMapper.selectById(id)
        return CommonUtil.convert(accountDO, AccountVO::class.java)
    }

    override fun saveOrUpdate(accountVO: AccountVO) {
        val accountDO = CommonUtil.convert(accountVO, AccountDO::class.java)
        if (accountDO != null) {
            if (accountDO.id == null) {
                accountDOMapper.insert(accountDO)
            } else {
                accountDOMapper.update(accountDO, null)
            }
        }
    }
}