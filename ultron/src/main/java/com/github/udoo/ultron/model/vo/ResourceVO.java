package com.github.udoo.ultron.model.vo;

import com.github.udoo.ultron.common.group.Insert;
import com.github.udoo.ultron.common.group.Update;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author dong.yang
 * @data 2019/7/13 19:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceVO {
    private Long id;

    @NotBlank(message = "code不能为空", groups = {Insert.class})
    @Size(max = 64, message = "长度不能超过64个字符", groups = {Insert.class})
    private String code;

    @Size(max = 32, message = "名称不能超过32个字符", groups = {Insert.class, Update.class})
    private String name;

    @Size(max = 64, message = "路径不能超过64个字符", groups = {Insert.class, Update.class})
    private String path;

    private Integer creater;
    private Date createdAt;
    private Integer modifier;
    private Date updatedAt;
}
