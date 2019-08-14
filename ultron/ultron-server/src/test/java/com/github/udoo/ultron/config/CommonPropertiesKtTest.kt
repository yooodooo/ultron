package com.github.udoo.ultron.config

import com.alibaba.fastjson.JSON
import com.github.udoo.ultron.config.properties.CommonProperties
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.Test
import kotlin.test.junit.JUnitAsserter.assertEquals

/**
 * @author dong.yang
 * @data 2019/7/17 17:47
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class CommonPropertiesKtTest {

    @Autowired
    lateinit var commonProperties: CommonProperties

    @Test
    fun testCommon() {
        assertEquals("pass", "ultron", commonProperties.name)
    }

    @Test
    fun testJSON() {
        val env = Env(name = "aa", age = 2)
        val envParse = JSON.parseObject("{\"age\":2,\"name\":\"aa\"}", Env::class.java)
        assertEquals("", env.name, envParse.name)
    }
}

class Env(val name: String, val age: Int)