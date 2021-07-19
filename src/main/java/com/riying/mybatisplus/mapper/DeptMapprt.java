package com.riying.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.riying.mybatisplus.entity.Dept;

/**
 * @author: Mirai Zhao
 * @create: 2021-07-19  11:10
 * @Description: DeptMapper不需要使用的，MP需要使用DeptMapper获取数据库的表的信息
 *              如果不定义DeptMapper，MP会报错，找不到表的信息
 */
public interface DeptMapprt extends BaseMapper<Dept>  {

}
