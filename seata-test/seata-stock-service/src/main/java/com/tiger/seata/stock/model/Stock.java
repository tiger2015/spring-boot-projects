package com.tiger.seata.stock.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Zenghu
 * @Date 2023年12月17日 15:50
 * @Description
 * @Version: 1.0
 **/
@TableName(value = "t_stock")
@Data
public class Stock implements Serializable {
    private static final long serialVersionUID = 5274368589598872457L;
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField
    private String commodityCode;

    @TableField
    private String name;

    @TableField
    private Integer count;
}
