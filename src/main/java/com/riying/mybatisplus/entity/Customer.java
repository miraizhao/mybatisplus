package com.riying.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author: Mirai Zhao
 * @create: 2021-07-19  15:17
 * @Description:
 */
@Data
@TableName("customer")
public class Customer {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String custName;
    private int custAge;
    private String custEmail;
}
