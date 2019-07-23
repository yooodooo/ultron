package com.github.udoo.ultron.dao.domain

import com.baomidou.mybatisplus.annotations.TableField
import com.baomidou.mybatisplus.annotations.TableId
import com.baomidou.mybatisplus.annotations.TableName
import com.baomidou.mybatisplus.enums.FieldFill
import com.baomidou.mybatisplus.enums.FieldStrategy
import com.baomidou.mybatisplus.enums.IdType
import java.util.*

/**
 * @author dong.yang
 * @data 2019/7/23 9:53
 */
@TableName("tm_account")
class AccountDO {

    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null

    @TableField(fill = FieldFill.INSERT)
    var email: String? = null

    @TableField
    var name: String? = null

    @TableField(strategy = FieldStrategy.IGNORED)
    var passwd: String? = null

    @TableField(fill = FieldFill.INSERT)
    var creater: Int? = null

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    var createdAt: Date? = null

    var modifier: Int? = null

    @TableField("updated_at")
    var updatedAt: Date? = null
}