package com.riying.mybatisplus;

import com.riying.mybatisplus.entity.User;
import com.riying.mybatisplus.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("all")
@SpringBootTest
@Slf4j
class MybatisplusApplicationTests {
//    试用自动注入，注入mapper对象
    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
    }

    /**
     *     定义测试方法
     *     测试添加操作  insert
     */

    @Test
    public void testUserInsert(){
        //创建User对象
        User user=new User();
        user.setAge(18);
        user.setEmail("zhangsan@qq.com");
        user.setName("张三");
//        调用UserMapper的方法，也就是父接口BaseMapper中提供的方法
        for (int i = 0; i < 5; i++) {
            int insert = userMapper.insert(user);
        }
//        log.info("insert的结果："+insert);
    }

    /**
     * 添加数据后  获取主键值
     */
    @Test
    public void testInsertGetId(){
        //创建User对象
        User user=new User();
        user.setAge(20);
        user.setEmail("lisi@qq.com");
        user.setName("李四");
    //  调用UserMapper的方法，也就是父接口BaseMapper中提供的方法
        int insert = userMapper.insert(user);
        log.info("insert的结果："+insert);
//        获取主键id，刚添加数据库中的数据id
        Integer id = user.getId();
        log.info(id+"");
    }
    /**
     * 更新操作 update
     * UPDATE user SET name=?, email=?, age=? WHERE id=?
     * 更新了所有非空的属性值，条件where id =主键
     */
    @Test
    public void testUpdateUser(){
        User user=new User();
        user.setAge(22);
        user.setEmail("edit@qq.com");
        user.setName("修改的数据");
        user.setId(2);
        int i = userMapper.updateById(user);
        log.info(i+"");
    }
    /**
     *   控制更新的属性
     *   UPDATE user SET name=? WHERE id=?
     */
    @Test
    public void testUpdateUser2(){
        User user=new User();
//        user.setAge(22);
//        user.setEmail("edit@qq.com");
        user.setName("zhangsan");
        user.setId(2);
        int i = userMapper.updateById(user);
        log.info(i+"");
    }

    /**
     *   更新数据  实体类的属性是基本类型  int age
     * ==>  Preparing: UPDATE user SET email=?, age=? WHERE id=?
     * ==> Parameters: lisi@baidu.com(String), 0(Integer), 3(Integer)
     * 3	李四	lisi@baidu.com	0
     */
    @Test
    public void testUpdateUser3(){
        User user=new User();
//        user.setAge(22);
        user.setEmail("lisi@baidu.com");
//        user.setName("zhangsan");
        user.setId(3);
        int i = userMapper.updateById(user);
        log.info(i+"");
    }
    /**
     * 按住键删除一条数据
     * 方法是deleteById
     * 参数 主键值
     * 返回值 删除成功数
     * ==>  Preparing: DELETE FROM user WHERE id=?
     * ==> Parameters: 3(Integer)
     *
     * ==>  Preparing: DELETE FROM user WHERE id=?
     * ==> Parameters: 3(Integer)
     * <==    Updates: 0
     */
    @Test
    public void testDeleteById(){
        int i = userMapper.deleteById(3);
        log.info(i+"");
    }
    /**
     * 按条件删除数据，条件是封装到Map对象中
     *方法：deleteByMap(map对象 )
     * 返回值 ：删除成功的记录数
     *
     * ==>  Preparing: DELETE FROM user WHERE name = ? AND age = ?
     * ==> Parameters: zs(String), 20(Integer)
     * <==    Updates: 1
     */
    @Test
    public void testDeleteByMap(){
        //创建map对象，保存条件值
        Map<String,Object> map=new HashMap<>();
        //put("表的字段名",条件值),可以封装多个条件
        map.put("name","zs");
        map.put("age",20);
        //调用删除方法
        int i = userMapper.deleteByMap(map);
        log.info(i+"");
    }
    /**
     * 批处理方式：使用多个主键值，删除数据
     * 方法名：deleteBatchIds()
     * 参数：Collection<? extends Serializable> var1
     * 返回值：删除的记录数
     *
     * ==>  Preparing: DELETE FROM user WHERE id IN ( ? , ? , ? , ? , ? )
     * ==> Parameters: 6(Integer), 2(Integer), 3(Integer), 4(Integer), 5(Integer)
     * <==    Updates: 4
     */
    @Test
    public void testDeleteBatchIds(){

    //        List<Integer> ids=new ArrayList<>();
    //        ids.add(6);
    //        ids.add(2);
    //        ids.add(3);
    //        ids.add(4);
    //        ids.add(5);
//        使用lambda创建List集合
        /**
         * ==>  Preparing: DELETE FROM user WHERE id IN ( ? , ? , ? , ? , ? )
         * ==> Parameters: 1(Integer), 2(Integer), 3(Integer), 4(Integer), 5(Integer)
         * <==    Updates: 0
         */
        List<Integer> list = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList());
        //调用删除方法
        int i = userMapper.deleteBatchIds(list);
        log.info(i+"");
    }

    /**
     * 实现查询 selectById 根据主键值查询
     * 参数：主键值
     * 返回值：实体对象
     *
     * ==>  Preparing: SELECT id,name,email,age FROM user WHERE id=?
     * ==> Parameters: 7(Integer)
     * <==    Columns: id, name, email, age
     * <==        Row: 7, 张三, zhangsan@qq.com, 18
     * <==      Total: 1
     */
    @Test
    public void testSelectById(){
        User user = userMapper.selectById(7);
        if (user!=null){
            System.out.println(user);
        }
    }

    /**
     * 实现批处理查询，根据多个主键值查询，获取到list
     * 方法:
     * 参数：id的集合
     * 返回值：List<T>
     *
     *     ==>  Preparing: SELECT id,name,email,age FROM user WHERE id IN ( ? , ? , ? , ? , ? )
     * ==> Parameters: 7(Integer), 8(Integer), 9(Integer), 10(Integer), 11(Integer)
     * <==    Columns: id, name, email, age
     * <==        Row: 7, 张三, zhangsan@qq.com, 18
     * <==        Row: 8, 张三, zhangsan@qq.com, 18
     * <==        Row: 9, 张三, zhangsan@qq.com, 18
     * <==        Row: 10, 张三, zhangsan@qq.com, 18
     * <==        Row: 11, 张三, zhangsan@qq.com, 18
     * <==      Total: 5
     */
    @Test
    public void testSelectBatchId(){
        List<Integer> list = Stream.of(7, 8, 9,10,11).collect(Collectors.toList());
        List<User> users = userMapper.selectBatchIds(list);
        log.info(users.size()+"");
        for (User u :
                users) {
            log.info(u + "");
        }
    }

    /**
     * 使用map做多条件查询
     * 方法selectbumap()
     * 参数 Map(String,Object)
     * 返回值： List<T>
     */
    @Test
    public void testSelectByMap(){
        //创建Map，封装查询条件
        Map<String,Object> map=new HashMap<>();
//      key是字段名，value是字段值 多个key 是and连接
        map.put("name","张三");
        map.put("age",20);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(user -> {
            log.info(user+"");
        });
    }

}
