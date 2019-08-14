package com.github.udoo.ultron.api;

import com.github.udoo.api.AccountApi;
import com.github.udoo.bo.AccountBO;
import com.github.udoo.dto.AccountDTO;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

/**
 * @author dong.yang
 * @data 2019/8/14 15:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountApiTest {

    @Reference(validation = "true", version = "${dubbo.provider.AccountApi.version}")
    private AccountApi accountApi;

    @Test
    public void testGetAccount() {
        AccountBO accountBO = accountApi.getAccount(1);
        Assert.assertNotNull(accountBO);
        Assert.assertEquals("je", accountBO.getCode());
    }

    @Test
    public void testCreateAccountPass() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setCode("hello");
        accountDTO.setName("hello name");

        AccountBO accountBO = accountApi.createAccount(accountDTO);
        Assert.assertNotNull(accountBO);
        Assert.assertEquals(1, accountBO.getId(), 0);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testCreateAccountValidate() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setCode("");
        accountDTO.setName("hello name");
        accountApi.createAccount(accountDTO);
    }
}
