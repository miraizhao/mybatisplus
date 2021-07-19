package com.riying.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.riying.mybatisplus.entity.User;

/**
 * @author: Mirai Zhao
 * @create: 2021-07-17  16:44
 * @Description: 自定义的mapper，就是Dao接口
 *  1、需要继承BaseMapper
 *  2、指定实体类
 *
 *  BaseMapper是MP框架中的对象，定义了17个操作方法
 */
public interface UserMapper extends BaseMapper<User> {
}
