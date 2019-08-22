package com.github.udoo.ultron.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.github.udoo.ultron.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dong.yang
 * @data 2019/8/22 15:59
 */
@RestController
@RequestMapping(value = "/nacos")
public class NacosController {

    @NacosValue(value = "${nacos.version:0}", autoRefreshed = true)
    private String nacosVersion;

    @NacosValue(value = "${nacos.hello:a}", autoRefreshed = true)
    private String nacosHello;


    @RequestMapping(value = "/list.json", method = RequestMethod.GET)
    public Result list() {
        Map<String, String> param = new HashMap<>();
        param.put("version", nacosVersion);
        param.put("hello", nacosHello);
        return Result.success(param);
    }
}
