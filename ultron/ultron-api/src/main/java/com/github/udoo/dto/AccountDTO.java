package com.github.udoo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author dong.yang
 * @data 2019/8/13 16:19
 */
@Data
public class AccountDTO {
    @NotBlank(message = "code不能为空")
    @Size(max = 32, message = "code长度不能超过32个字符")
    private String code;

    @NotBlank(message = "name不能为空")
    @Size(max = 32, message = "name长度不能超过32个字符")
    private String name;
}
