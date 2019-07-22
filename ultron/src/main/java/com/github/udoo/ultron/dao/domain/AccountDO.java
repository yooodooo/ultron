package com.github.udoo.ultron.dao.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * @author dong.yang
 * @data 2019/7/22 14:09
 */
@Data
@TableName("tm_account")
public class AccountDO {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(fill = FieldFill.INSERT)
    private String email;

    @TableField
    private String name;

    @TableField(strategy = FieldStrategy.IGNORED)
    private String passwd;

    @TableField(fill = FieldFill.INSERT)
    private Integer creater;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private Date createdAt;

    private Integer modifier;

    @TableField("updated_at")
    private Date updatedAt;
}
