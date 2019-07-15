package com.github.udoo.ultron.model.vo;

import com.github.udoo.ultron.common.group.Insert;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author dong.yang
 * @data 2019/7/13 19:13
 */
@Data
public class ResourceVO {
    private Long id;

    @NotBlank(message = "code不能为空", groups = {Insert.class})
    @Size(max = 64, message = "长度不能超过64个字符")
    private String code;

    @Size(max = 64, message = "名称不能超过64个字符")
    private String name;

    @Size(max = 64, message = "路径不能超过64个字符")
    private String path;

    private Integer creater;
    private Date createdAt;
    private Integer modifier;
    private Date updatedAt;
}
