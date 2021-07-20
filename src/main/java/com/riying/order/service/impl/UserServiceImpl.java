package com.riying.order.service.impl;

import com.riying.order.entity.User;
import com.riying.order.mapper.UserMapper;
import com.riying.order.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
