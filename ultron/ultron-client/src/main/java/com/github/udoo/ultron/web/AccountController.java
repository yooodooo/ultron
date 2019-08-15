package com.github.udoo.ultron.web;

import com.github.udoo.api.AccountApi;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dong.yang
 * @data 2019/8/15 10:01
 */
@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Reference(validation = "true", version = "${dubbo.provider.AccountApi.version}")
    private AccountApi accountApi;

    @GetMapping(value = "/{id}.json")
    public Object get(@PathVariable("id") Integer id) {
        return accountApi.getAccount(id);
    }

}
