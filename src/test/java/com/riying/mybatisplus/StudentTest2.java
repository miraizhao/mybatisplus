package com.riying.mybatisplus;

import com.riying.order.entity.Student;
import com.riying.order.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Mirai Zhao
 * @create: 2021-07-20  15:09
 * @Description:
 */
@SuppressWarnings("all")
@SpringBootTest
public class StudentTest2 {
    @Autowired
    StudentMapper studentMapper;
    @Test
    public void testinsertStudent(){
        Student student=new Student();
        student.setName("小明");
        student.setStatus(1);
        student.setEmail("www.com");
        student.setAge(19);
        studentMapper.insert(student);
    }
    @Test
    public void testSelect(){
        Student student = studentMapper.selectById(1);
        System.out.println(student);
    }
}
