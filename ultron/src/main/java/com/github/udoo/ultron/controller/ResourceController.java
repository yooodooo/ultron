package com.github.udoo.ultron.controller;

import com.github.udoo.ultron.common.Result;
import com.github.udoo.ultron.service.system.ResourceQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dong.yang
 * @data 2019/7/15 14:05
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    ResourceQueryService resourceQueryService;

    @RequestMapping(value = "/{id}.json")
    public Result query(@PathVariable("id") Long id) {
        return Result.success(resourceQueryService.queryById(id));
    }
}
