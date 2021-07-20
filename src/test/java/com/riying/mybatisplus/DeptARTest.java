package com.riying.mybatisplus;

import com.riying.mybatisplus.entity.Dept;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Mirai Zhao
 * @create: 2021-07-19  11:33
 * @Description:
 */
@SpringBootTest
@Slf4j
public class DeptARTest {
    @Test
    void contextLoads() {
    }

    /**
     * ==>  Preparing: INSERT INTO dept ( name, mobile, manager ) VALUES ( ?, ?, ? )
     * ==> Parameters: 销售部(String), 010-1234(String), 1(Integer)
     * <==    Updates: 1
     */
    @Test
    public void testARInsert(){
//         定义dept的实体
        Dept dept=new Dept();
        dept.setManager(1);
        dept.setName("销售部");
        dept.setMobile("010-1234");
//        调用实体自己的方法，完成对象自身到数据库的添加操作
        boolean insert = dept.insert();
        log.info(insert+"");
    }

    /**
     * ==>  Preparing: UPDATE dept SET name=?, mobile=?, manager=? WHERE id=?
     * ==> Parameters: 市场部(String), 010-22222(String), 2(Integer), 1(Integer)
     * <==    Updates: 1
     */
    @Test
    public void testARUpdate(){
//         定义dept的实体
        Dept dept=new Dept();
//        dept.setId(1);
//        dept.setManager(2);
//        dept.setName("市场部");
        dept.setMobile("010-333333");
//        根据主键id更新记录
        boolean insert = dept.updateById();//使用dept实体类主键的值，作为where id=1
        log.info(insert+"");
    }

    /**
     * 根据id删除
     */
    @Test
    public void testDeleteById(){
        Dept dept=new Dept();
//        dept.setId(1);
        boolean b = dept.deleteById();
        log.info(b+"");
    }

    /**
     * ==>  Preparing: SELECT id,name,mobile,manager FROM dept WHERE id=?
     * ==> Parameters: 2(Integer)
     * <==    Columns: id, name, mobile, manager
     * <==        Row: 2, 销售部, 010-1234, 1
     * <==      Total: 1
     *
     *
     * ==>  Preparing: SELECT id,name,mobile,manager FROM dept WHERE id=?
     * ==> Parameters: 1(Integer)
     * <==      Total: 0
     */
    @Test
    public void testSelectById(){
        Dept dept=new Dept();
//        设置主键的值
//        dept.setId(1);
//        调用查询方法
        Dept dept1 = dept.selectById();
        System.out.println(dept1);
    }

    /**
     * ==>  Preparing: SELECT id,name,mobile,manager FROM dept WHERE id=?
     * ==> Parameters: 2(Integer)
     * <==    Columns: id, name, mobile, manager
     * <==        Row: 2, 销售部, 010-1234, 1
     * <==      Total: 1
     *
     *
     * ==>  Preparing: SELECT id,name,mobile,manager FROM dept WHERE id=?
     * ==> Parameters: 1(Integer)
     * <==      Total: 0
     *
     * ==>  Preparing: SELECT id,name,mobile,manager FROM dept WHERE id=?
     * ==> Parameters: null
     * <==      Total: 0
     */
    @Test
    public void testSelectById2(){
        Dept dept=new Dept();
//        调用查询方法
        Dept dept1 = dept.selectById(null);
        System.out.println(dept1);
    }

}
