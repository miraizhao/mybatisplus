package com.riying.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.riying.mybatisplus.entity.Address;
import com.riying.mybatisplus.entity.Student;
import com.riying.mybatisplus.mapper.AddressMapper;
import com.riying.mybatisplus.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: Mirai Zhao
 * @create: 2021-07-19  11:33
 * @Description:
 */
@SuppressWarnings("all")
@SpringBootTest
@Slf4j
public class StudentTest {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    void contextLoads() {
    }


    @Test
    public void testInsertStudent() {
        Student student = new Student();
        student.setName("李四");
        student.setAge(99);
        student.setStatus(1);
        student.setEmail("li@123.com");
        int insert = studentMapper.insertSudent(student);
        System.out.println(insert);
    }

    @Test
    public void testSelectStudentById() {
        Student student = studentMapper.selectStudentById(1);
        System.out.println(student);
    }

    @Test
    public void testSelectStudentByName() {
        List<Student> students = studentMapper.selectStudentByName("李四");
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (name = ? AND age = ?)
     * ==> Parameters: 张三(String), 18(Integer)
     * <==    Columns: id, name, age, email, status
     * <==        Row: 3, 张三, 18, 123@456.com, 1
     * <==      Total: 1
     */
    @Test
    public void testAllEq() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
//        组装条件
        Map<String, Object> param = new HashMap<>();
//        map<key,value> key:列名 ,value:查询的值
        param.put("name", "张三");
        param.put("age", 18);
        qw.allEq(param);
//        调用查询方法
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * 1).map对象中有key的value是null  使用  qw.allEq(param,true);
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (name = ? AND age IS NULL)
     * ==> Parameters: 张三(String)
     * <==      Total: 0
     * <p>
     * 2).map对象中有key的value是null  使用   qw.allEq(param,false);
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (name = ?)
     * ==> Parameters: 张三(String)
     * <==    Columns: id, name, age, email, status
     * <==        Row: 3, 张三, 18, 123@456.com, 1
     * <==      Total: 1
     * <p>
     * 结论    allEq(param,Boolean);  true：处理null值，where条件中加入 字段 is null
     * false：不处理null值，忽略 不作为where条件
     */
    @Test
    public void testAllEq2() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
//        组装条件
        Map<String, Object> param = new HashMap<>();
//        map<key,value> key:列名 ,value:查询的值
        param.put("name", "张三");
//        age是null
        param.put("age", null);
//        allEq第二个参数为true
        qw.allEq(param, false);
//        调用查询方法
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * eq使用
     * eq("列名",值)
     * <p>
     * <p>
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (name = ?)
     * ==> Parameters: 李四(String)
     * <==    Columns: id, name, age, email, status
     * <==        Row: 1, 李四, 20, 123@123.com, 1
     * <==        Row: 2, 李四, 99, li@123.com, 1
     * <==      Total: 2
     */
    @Test
    public void testEq() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.eq("name", "李四");
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * ne使用
     * ne表示不等于
     * ne("列名",值）
     * <p>
     * <p>
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (name <> ?)
     * ==> Parameters: 张三(String)
     * <==    Columns: id, name, age, email, status
     * <==        Row: 1, 李四, 20, 123@123.com, 1
     * <==        Row: 2, 李四, 99, li@123.com, 1
     * <==        Row: 4, 张四, 84, 456@789.com, 1
     * <==        Row: 5, 张五, 77, 789@123.com, 1
     * <==      Total: 4
     */
    @Test
    public void testNe() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.ne("name", "张三");
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * gt使用
     * gt表示大于
     * <p>
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (age > ?)
     * ==> Parameters: 20(Integer)
     * <==    Columns: id, name, age, email, status
     * <==        Row: 2, 李四, 99, li@123.com, 1
     * <==        Row: 4, 张四, 84, 456@789.com, 1
     * <==        Row: 5, 张五, 77, 789@123.com, 1
     * <==      Total: 3
     */
    @Test
    public void testGt() {
        QueryWrapper<Student> qw = new QueryWrapper();
        qw.gt("age", 20);//age>20
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * ge
     * ge表示大于等于
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (age >= ?)
     * ==> Parameters: 77(Integer)
     * <==    Columns: id, name, age, email, status
     * <==        Row: 2, 李四, 99, li@123.com, 1
     * <==        Row: 4, 张四, 84, 456@789.com, 1
     * <==        Row: 5, 张五, 77, 789@123.com, 1
     * <==      Total: 3
     */
    @Test
    public void testGe() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.ge("age", 77);
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * lt
     * lt表示小于
     * <p>
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (age < ?)
     * ==> Parameters: 20(Integer)
     * <==    Columns: id, name, age, email, status
     * <==        Row: 3, 张三, 18, 123@456.com, 1
     * <==      Total: 1
     */
    @Test
    public void testLt() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.lt("age", 20);
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * le
     * le表示小于等于
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (age <= ?)
     * ==> Parameters: 77(Integer)
     * <==    Columns: id, name, age, email, status
     * <==        Row: 1, 李四, 20, 123@123.com, 1
     * <==        Row: 3, 张三, 18, 123@456.com, 1
     * <==        Row: 5, 张五, 77, 789@123.com, 1
     * <==      Total: 3
     */
    @Test
    public void testLe() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.le("age", 77);
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * between
     * between表示在之间
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (age BETWEEN ? AND ?)
     * ==> Parameters: 77(Integer), 99(Integer)
     * <==    Columns: id, name, age, email, status
     * <==        Row: 2, 李四, 99, li@123.com, 1
     * <==        Row: 4, 张四, 84, 456@789.com, 1
     * <==        Row: 5, 张五, 77, 789@123.com, 1
     * <==      Total: 3
     */
    @Test
    public void testBetween() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.between("age", 77, 99);
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * notbetween
     * between表示不在范围内
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (age NOT BETWEEN ? AND ?)
     * ==> Parameters: 77(Integer), 84(Integer)
     * <==    Columns: id, name, age, email, status
     * <==        Row: 1, 李四, 20, 123@123.com, 1
     * <==        Row: 2, 李四, 99, li@123.com, 1
     * <==        Row: 3, 张三, 18, 123@456.com, 1
     * <==      Total: 3
     */
    @Test
    public void testNotBetween() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.notBetween("age", 77, 84);
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * like
     * like表示匹配某个值
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (name LIKE ?)
     * ==> Parameters: %张%(String)
     * <==    Columns: id, name, age, email, status
     * <==        Row: 3, 张三, 18, 123@456.com, 1
     * <==        Row: 4, 张四, 84, 456@789.com, 1
     * <==        Row: 5, 张五, 77, 789@123.com, 1
     * <==      Total: 3
     */
    @Test
    public void testLike() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.like("name", "张");
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * notlike
     * notlike表示不匹配某个值
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (name NOT LIKE ?)
     * ==> Parameters: %张%(String)
     * <==    Columns: id, name, age, email, status
     * <==        Row: 1, 李四, 20, 123@123.com, 1
     * <==        Row: 2, 李四, 99, li@123.com, 1
     * <==      Total: 2
     */
    @Test
    public void testNotLike() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.notLike("name", "张");
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * likeRight  右侧加通配符
     * likeRight 以XXX开头
     * <p>
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (name LIKE ?)
     * ==> Parameters: 张%(String)
     * <==    Columns: id, name, age, email, status
     * <==        Row: 3, 张三, 18, 123@456.com, 1
     * <==        Row: 4, 张四, 84, 456@789.com, 1
     * <==        Row: 5, 张五, 77, 789@123.com, 1
     * <==      Total: 3
     */
    @Test
    public void testLikeRight() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.likeRight("name", "张");
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * likeLeft  左侧加通配符
     * likeLeft 以XXX结束
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (name LIKE ?)
     * ==> Parameters: %四(String)
     * <==    Columns: id, name, age, email, status
     * <==        Row: 1, 李四, 20, 123@123.com, 1
     * <==        Row: 2, 李四, 99, li@123.com, 1
     * <==        Row: 4, 张四, 84, 456@789.com, 1
     * <==      Total: 3
     */
    @Test
    public void testLikeLeft() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.likeLeft("name", "四");
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * isNull  判断字段是null
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (email IS NULL)
     * ==> Parameters:
     * <==    Columns: id, name, age, email, status
     * <==        Row: 6, 张六, 23, null, 1
     * <==      Total: 1
     */
    @Test
    public void testIsnull() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.isNull("email");
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * isNotNull  判断字段是null
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (email IS NOT NULL)
     * ==> Parameters:
     * <==    Columns: id, name, age, email, status
     * <==        Row: 1, 李四, 20, 123@123.com, 1
     * <==        Row: 2, 李四, 99, li@123.com, 1
     * <==        Row: 3, 张三, 18, 123@456.com, 1
     * <==        Row: 4, 张四, 84, , 1
     * <==        Row: 5, 张五, 77, , 1
     * <==      Total: 5
     */
    @Test
    public void testIsNotNull() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.isNotNull("email");
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * in  值列表
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (name IN (?,?))
     * ==> Parameters: 张三(String), 李四(String)
     * <==    Columns: id, name, age, email, status
     * <==        Row: 1, 李四, 20, 123@123.com, 1
     * <==        Row: 2, 李四, 99, li@123.com, 1
     * <==        Row: 3, 张三, 18, 123@456.com, 1
     * <==      Total: 3
     */
    @Test
    public void testIn() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.in("name", "张三", "李四");
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * in  值列表
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (status IN (?,?))
     * ==> Parameters: 1(Integer), 2(Integer)
     * <==    Columns: id, name, age, email, status
     * <==        Row: 1, 李四, 20, 123@123.com, 1
     * <==        Row: 2, 李四, 99, li@123.com, 2
     * <==        Row: 3, 张三, 18, 123@456.com, 1
     * <==      Total: 3
     */
    @Test
    public void testIn2() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        List<Integer> list = Stream.of(1, 2).collect(Collectors.toList());
        qw.in("status", list);
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * notin  不在值列表
     * <p>
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (name NOT IN (?,?))
     * ==> Parameters: 张三(String), 李四(String)
     * <==    Columns: id, name, age, email, status
     * <==        Row: 4, 张四, 84, , 14
     * <==        Row: 5, 张五, 77, , 15
     * <==        Row: 6, 张六, 23, null, 16
     * <==      Total: 3
     */
    @Test
    public void testNotIn() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.notIn("name", "张三", "李四");
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * inSql
     * 使用子查询
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (age IN (select age from student where id=1))
     * ==> Parameters:
     * <==    Columns: id, name, age, email, status
     * <==        Row: 1, 李四, 20, 123@123.com, 1
     * <==        Row: 6, 张六, 20, null, 16
     * <==      Total: 2
     */
    @Test
    public void testInSql() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.inSql("age", "select age from student where id=1");
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * notinSql
     * 使用子查询
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (age NOT IN (select age from student where id=1))
     * ==> Parameters:
     * <==    Columns: id, name, age, email, status
     * <==        Row: 2, 李四, 99, li@123.com, 2
     * <==        Row: 3, 张三, 18, 123@456.com, 1
     * <==        Row: 4, 张四, 84, , 14
     * <==        Row: 5, 张五, 77, , 15
     * <==      Total: 4
     */
    @Test
    public void testNotInSql() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.notInSql("age", "select age from student where id=1");
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * groupBy
     * 使用子查询
     * <p>
     * ==>  Preparing: SELECT name,count(*) personNumbers FROM student GROUP BY name
     * ==> Parameters:
     * <==    Columns: name, personNumbers
     * <==        Row: 李四, 2
     * <==        Row: 张三, 2
     * <==        Row: 张四, 1
     * <==        Row: 张六, 1
     * <==      Total: 4
     */
    @Test
    public void testGroupBy() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.select("name,count(*) personNumbers");
        qw.groupBy("name");
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * orderbyAsc
     * 使用子查询
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student ORDER BY name ASC,age ASC
     * ==> Parameters:
     * <==    Columns: id, name, age, email, status
     * <==        Row: 3, 张三, 18, 123@456.com, 1
     * <==        Row: 5, 张三, 77, , 15
     * <==        Row: 6, 张六, 20, null, 16
     * <==        Row: 4, 张四, 84, , 14
     * <==        Row: 1, 李四, 20, 123@123.com, 1
     * <==        Row: 2, 李四, 99, li@123.com, 2
     * <==      Total: 6
     */
    @Test
    public void testOrderbyAsc() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.orderByAsc("name", "age");
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * orderbyDesc
     * 使用子查询
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student ORDER BY name DESC,age DESC
     * ==> Parameters:
     * <==    Columns: id, name, age, email, status
     * <==        Row: 2, 李四, 99, li@123.com, 2
     * <==        Row: 1, 李四, 20, 123@123.com, 1
     * <==        Row: 4, 张四, 84, , 14
     * <==        Row: 6, 张六, 20, null, 16
     * <==        Row: 5, 张三, 77, , 15
     * <==        Row: 3, 张三, 18, 123@456.com, 1
     * <==      Total: 6
     */
    @Test
    public void testOrderByDesc() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.orderByDesc("name", "age");
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * order
     * 使用子查询
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student ORDER BY name ASC
     * ==> Parameters:
     * <==    Columns: id, name, age, email, status
     * <==        Row: 3, 张三, 18, 123@456.com, 1
     * <==        Row: 5, 张三, 77, , 15
     * <==        Row: 6, 张六, 20, null, 16
     * <==        Row: 4, 张四, 84, , 14
     * <==        Row: 1, 李四, 20, 123@123.com, 1
     * <==        Row: 2, 李四, 99, li@123.com, 2
     * <==      Total: 6
     */
    @Test
    public void testOrder() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.orderBy(true, false, "name").orderBy(true, true, "age");
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * or,and
     * 使用子查询
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (name = ? OR age = ?)
     * ==> Parameters: 张三(String), 22(Integer)
     * <==    Columns: id, name, age, email, status
     * <==        Row: 1, 李四, 22, 123@123.com, 1
     * <==        Row: 3, 张三, 18, 123@456.com, 1
     * <==        Row: 5, 张三, 77, , 15
     * <==      Total: 3
     */
    @Test
    public void testOr() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.eq("name", "张三").or().eq("age", 22);
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * last
     * 拼接sql语句道mp德sql语句末尾
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (name = ? OR age = ?) limit 1
     * ==> Parameters: 张三(String), 22(Integer)
     * <==    Columns: id, name, age, email, status
     * <==        Row: 1, 李四, 22, 123@123.com, 1
     * <==      Total: 1
     */
    @Test
    public void testLast() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.eq("name", "张三")
                .or()
                .eq("age", 22)
                .last("limit 1");
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * exists  判断条件
     * <p>
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (EXISTS (select id from student where age >20))
     * ==> Parameters:
     * <==    Columns: id, name, age, email, status
     * <==        Row: 1, 李四, 22, 123@123.com, 1
     * <==        Row: 2, 李四, 99, li@123.com, 2
     * <==        Row: 3, 张三, 18, 123@456.com, 1
     * <==        Row: 4, 张四, 84, , 14
     * <==        Row: 5, 张三, 77, , 15
     * <==        Row: 6, 张六, 20, null, 16
     * <==      Total: 6
     */
    @Test
    public void testExists() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.exists("select id from student where age >100");
        System.out.println(qw);
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * notexists  判断条件
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,status FROM student WHERE (NOT EXISTS (select id from student where age >100))
     * ==> Parameters:
     * <==    Columns: id, name, age, email, status
     * <==        Row: 1, 李四, 22, 123@123.com, 1
     * <==        Row: 2, 李四, 99, li@123.com, 2
     * <==        Row: 3, 张三, 18, 123@456.com, 1
     * <==        Row: 4, 张四, 84, , 14
     * <==        Row: 5, 张三, 77, , 15
     * <==        Row: 6, 张六, 20, null, 16
     * <==      Total: 6
     */
    @Test
    public void testNotExists() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.notExists("select id from student where age >100");
        System.out.println(qw);
        List<Student> students = studentMapper.selectList(qw);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    @Test
    public void testPage(){
        QueryWrapper<Student> qw=new QueryWrapper();
        qw.gt("age",20);
        IPage<Student> page=new Page<>();
        page.setCurrent(1);
        page.setSize(3);
        IPage<Student> result = studentMapper.selectPage(page, qw);
        List<Student> students = result.getRecords();
        System.out.println(students.size()+"student.size");
        System.out.println(result.getPages()+"页数");
        System.out.println(result.getTotal()+"总记录数");
        System.out.println(result.getCurrent()+"d当前页码");
        System.out.println(result.getSize()+"每页记录数");
        students.forEach(student -> System.out.println(student));

    }

}
