package com.riying;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author: Mirai Zhao
 * @create: 2021-07-20  14:17
 * @Description:
 */
public class AutoMapper {
    public static void main(String[] args) {
//        创建AutoGenerator，mp中对象
        AutoGenerator ag=new AutoGenerator();
//        设置全局配置
        GlobalConfig gc=new GlobalConfig();
//        设置生成位置，磁盘目录
        String path=System.getProperty("user.dir");
        gc.setOutputDir(path+"/src/main/java");
//        设置生成类的名称  //所有的dao都是Mapper结尾的
        gc.setMapperName("%sMapper");
//        设置Service接口的名称
        gc.setServiceName("%sService");
//        设置Service实现类的名称
        gc.setServiceImplName("%sServiceImpl");
//        设置Controller类的名称
        gc.setControllerName("%sController");
//        设置作者的名称
        gc.setAuthor("mirai zhao");
//        设置主键id的配置
        gc.setIdType(IdType.ASSIGN_ID);
        ag.setGlobalConfig(gc);
//        设置数据源DataSource
        DataSourceConfig ds=new DataSourceConfig();
//        驱动
        ds.setDriverName("com.mysql.cj.jdbc.Driver");
//        设置url
        ds.setUrl("jdbc:mysql://1.117.31.221/springdb_mybatisplus?useUnicode=true&characterEncoding=utf-8");
//        设置用户名
        ds.setUsername("root");
//        设置密码
        ds.setPassword("123456");
//        把DataSource赋值给ag
        ag.setDataSource(ds);
//        设置package信息
        PackageConfig pc=new PackageConfig();
//        设置模块名称，相当于子包名，在这个包下面有mapper，serveice，Controller
        pc.setModuleName("order");
//        设置父包名称，order在父包的下面生成
        pc.setParent("com.riying");
        ag.setPackageInfo(pc);
//        设置策略
        StrategyConfig sc=new StrategyConfig();
//        设置支持驼峰命名规则
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        sc.setNaming(NamingStrategy.underline_to_camel);
        ag.setStrategy(sc);
//        指向代码的生成
        ag.execute();
    }
}
