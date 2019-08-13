package com.github.udoo.bo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author dong.yang
 * @data 2019/8/13 18:06
 */
@Data
@Builder
public class AccountBO implements Serializable {
    private String code;
    private String name;
}
