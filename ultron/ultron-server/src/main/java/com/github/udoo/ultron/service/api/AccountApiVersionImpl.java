package com.github.udoo.ultron.service.api;

import com.github.udoo.api.AccountApi;
import com.github.udoo.bo.AccountBO;
import com.github.udoo.dto.AccountDTO;
import org.apache.dubbo.config.annotation.Method;
import org.springframework.stereotype.Service;

/**
 * @author dong.yang
 * @data 2019/8/22 9:58
 */
@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", group = "group.account", version = "${dubbo.provider.AccountApi.version.v2}",
        timeout = 1000, methods = {
        @Method(name = "getAccount", timeout = 200)
})
public class AccountApiVersionImpl implements AccountApi {

    @Override
    public AccountBO getAccount(Integer id) {
        return AccountBO.builder()
                .id(id)
                .code("je.from version 2.0.0")
                .name("la")
                .build();
    }

    @Override
    public AccountBO createAccount(AccountDTO accountDTO) {
        return null;
    }
}
