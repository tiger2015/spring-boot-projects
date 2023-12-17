package com.tiger.seata.order.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Zenghu
 * @Date 2023年12月17日 16:19
 * @Description
 * @Version: 1.0
 **/
@Data
@TableName(value = "t_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 4049178889955450103L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField
    private String orderNo;

    @TableField
    private String userId;

    @TableField
    private String commodityCode;

    @TableField
    private Integer count;

    @TableField
    private Double amount;

}
