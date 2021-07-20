package com.riying.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author: Mirai Zhao
 * @create: 2021-07-19  14:50
 * @Description: 1.@TableName(value="表名") 位置：类定义的上面
 *               2.@TableField：指定属性和列名对应关系   属性 value 指定列名
 */

@TableName(value = "user_address")
@Data
public class Address {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("user_city")
    private String city;
    @TableField("user_street")
    private String street;
    private String zipcode;
}
