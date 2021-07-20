package com.riying.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.riying.mybatisplus.entity.Student;

import java.util.List;

/**
 * @author: Mirai Zhao
 * @create: 2021-07-19  15:29
 * @Description:
 */
public interface StudentMapper extends BaseMapper<Student> {
    //自定义方法
    public int insertSudent(Student student);
    public List<Student> selectStudentByName(String name);
    public Student selectStudentById(Integer id);
}
