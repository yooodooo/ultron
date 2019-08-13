package com.github.udoo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author dong.yang
 * @data 2019/8/13 16:19
 */
@Data
@Accessors(chain = true)
public class AccountDTO implements Serializable {
    private String code;
    private String name;
}
