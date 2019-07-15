package com.github.udoo.ultron.dao.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * @author dong.yang
 * @data 2019/7/13 16:12
 */
@Data
@TableName("tm_resource")
public class ResourceDO {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(strategy = FieldStrategy.IGNORED)
    private String path;

    @TableField(strategy = FieldStrategy.IGNORED)
    private String name;
}
