package com.github.udoo.ultron.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author dong.yang
 * @data 2019/7/19 11:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class RedisEntry implements Serializable {
    private String name;
    private String age;
}
