package com.riying.mybatisplus;

import com.riying.mybatisplus.entity.Address;
import com.riying.mybatisplus.entity.Dept;
import com.riying.mybatisplus.mapper.AddressMapper;
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
public class AddressTest {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    void contextLoads() {
    }

    /**
     * ==>  Preparing: INSERT INTO user_address ( city, street, zipcode ) VALUES ( ?, ?, ? )
     * ==> Parameters: 北京(String), 长安大街(String), 00000(String)
     * <==    Updates: 1
     * ==>  Preparing: INSERT INTO user_address ( user_city, user_street, zipcode ) VALUES ( ?, ?, ? )
     * ==> Parameters: 上海(String), 南京路(String), 00000(String)
     * <==    Updates: 1
     */
    @Test
    void testInsert() {
        Address address = new Address();
        address.setCity("上海");
        address.setStreet("南京路");
        address.setZipcode("00000");
        int insert = addressMapper.insert(address);
        System.out.println(insert);
    }

}
