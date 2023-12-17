package com.tiger.seata.account.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Zenghu
 * @Date 2023年12月17日 16:25
 * @Description
 * @Version: 1.0
 **/
@Data
@TableName(value = "t_account")
public class Account implements Serializable {
    private static final long serialVersionUID = 2931352051539346024L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField
    private String userId;

    @TableField
    private Double amount;
}
