package com.riying.order.service.impl;

import com.riying.order.entity.Student;
import com.riying.order.mapper.StudentMapper;
import com.riying.order.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mirai zhao
 * @since 2021-07-20
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
