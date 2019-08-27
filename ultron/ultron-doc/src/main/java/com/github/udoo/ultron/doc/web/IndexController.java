package com.github.udoo.ultron.doc.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dong.yang
 * @date 2019/8/27 9:29
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "success";
    }
}