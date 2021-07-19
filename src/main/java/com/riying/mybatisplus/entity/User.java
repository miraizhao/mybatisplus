package com.riying.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author: Mirai Zhao
 * @create: 2021-07-17  16:38
 * @Description: 实体类
 */
@Data
public class User {
    /**
     * 指定主键的方式
     * value：主键字段的名称：如果是id，可以不用写
     * type：指定主键的类型 主键的值如何生成 IdType.AUTO 表示自动增长
     */
    @TableId(
            value = "id",
            type = IdType.AUTO
    )
    private Integer id;
    private String name;
    private String email;
//    实体类型的属性，推荐使用包装类型，可以判断是否为空 null
    private Integer age;
}
