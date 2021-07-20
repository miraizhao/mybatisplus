package com.riying.mybatisplus;

import com.riying.mybatisplus.entity.Address;
import com.riying.mybatisplus.entity.Customer;
import com.riying.mybatisplus.mapper.AddressMapper;
import com.riying.mybatisplus.mapper.CustomerMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Mirai Zhao
 * @create: 2021-07-19  11:33
 * @Description:
 */
@SuppressWarnings("all")
@SpringBootTest
@Slf4j
public class CustomerTest {
    @Autowired
    private CustomerMapper customerMapper;

    @Test
    void contextLoads() {
    }

    /**
     *
     */
    @Test
    void testInsert() {
        Customer customer = new Customer();
        customer.setCustAge(28);
        customer.setCustEmail("aaa@qq.com");
        customer.setCustName("张三");
        int insert = customerMapper.insert(customer);
        System.out.println(insert);
    }

}
