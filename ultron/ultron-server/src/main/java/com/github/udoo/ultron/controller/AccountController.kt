package com.github.udoo.ultron.controller

import com.github.udoo.ultron.common.Result
import com.github.udoo.ultron.common.group.Insert
import com.github.udoo.ultron.model.vo.AccountVO
import com.github.udoo.ultron.service.system.AccountService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * @author dong.yang
 * @data 2019/7/23 9:08
 */
@RestController
@RequestMapping("/account")
class AccountController(private val accountService: AccountService) {

    @GetMapping("/{id}.json")
    fun findOne(@PathVariable("id") id: Int): Result<Any> = Result.success(accountService.selectById(id))

    @PostMapping("/save.json")
    fun save(@Validated(value = [Insert::class]) @RequestBody account: AccountVO): Result<Any> {
        accountService.saveOrUpdate(account)
        return Result.success()
    }
}