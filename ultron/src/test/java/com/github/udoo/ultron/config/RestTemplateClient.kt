package com.github.udoo.ultron.config

import com.alibaba.fastjson.JSON
import com.github.udoo.ultron.common.Result
import com.github.udoo.ultron.model.vo.AccountVO
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.client.RestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity


/**
 * @author dong.yang
 * @data 2019/7/23 10:39
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class RestTemplateClient {

    @Autowired
    lateinit var restTemplate: RestTemplate

    @Test
    fun testCreateAccount() {
        var accountVO = AccountVO()
        accountVO.email = "test@test.com"
        accountVO.name = "name"
        accountVO.creater = 1
        accountVO.passwd = "11111"

        var headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val request = HttpEntity(JSON.toJSONString(accountVO), headers)

        val result: ResponseEntity<Result<Void>> = restTemplate.postForEntity("http://localhost:8090/ultron/account/save.json", request, Result<Void>().javaClass)
        println(result.body)
    }
}