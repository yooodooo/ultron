package com.github.udoo.ultron.service.system

import com.github.udoo.ultron.model.vo.AccountVO

interface AccountService {

    fun selectById(id: Long?): AccountVO

    fun saveOrUpdate(accountVO: AccountVO)
}