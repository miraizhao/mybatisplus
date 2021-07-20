package com.riying.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @author: Mirai Zhao
 * @create: 2021-07-19  10:49
 * @Description: 使用AR，要求实体类继承MP中的Modle
 * Model中提供了对数据库的CRUD的操作
 */
@Data
public class Dept extends Model<Dept>  {
//    定义属性，属性名和表的列明一样
    @TableId(value = "id",type = IdType.ID_WORKER_STR)
    private Long id;
    private String name;
    private String  mobile;
    private Integer manager;
}
