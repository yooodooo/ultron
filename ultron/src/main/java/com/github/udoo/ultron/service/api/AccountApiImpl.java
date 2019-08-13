package com.github.udoo.ultron.service.api;

import com.github.udoo.api.AccountApi;
import com.github.udoo.bo.AccountBO;
import org.springframework.stereotype.Service;

/**
 * @author dong.yang
 * @data 2019/8/13 16:51
 */
@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.AccountApi.version}")
public class AccountApiImpl implements AccountApi {

    @Override
    public AccountBO getAccount(Integer integer) {
        return AccountBO.builder()
                .code("je")
                .name("la")
                .build();
    }

}
