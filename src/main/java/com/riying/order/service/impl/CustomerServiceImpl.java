package com.riying.order.service.impl;

import com.riying.order.entity.Customer;
import com.riying.order.mapper.CustomerMapper;
import com.riying.order.service.CustomerService;
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
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
