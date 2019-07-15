package com.github.udoo.ultron.controller;

import com.github.udoo.ultron.common.Result;
import com.github.udoo.ultron.common.group.Insert;
import com.github.udoo.ultron.model.vo.ResourceVO;
import com.github.udoo.ultron.service.system.ResourceQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/{id}.json", method = {RequestMethod.GET})
    public Result query(@PathVariable("id") Long id) {
        return Result.success(resourceQueryService.queryById(id));
    }

    @RequestMapping(value = "/save.json", method = {RequestMethod.POST})
    public Result insert(@Validated(value = {Insert.class}) ResourceVO resourceVO) {
        return Result.success();
    }

    @RequestMapping(value = "/update.json", method = {RequestMethod.POST})
    public Result update(@Validated ResourceVO resourceVO) {
        return Result.success();
    }
}
