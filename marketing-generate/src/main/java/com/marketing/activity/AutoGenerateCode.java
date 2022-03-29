package com.marketing.activity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

import java.util.Collections;


/**
 * mybaits-plus 代码生成器
 * @author yyz
 */
public class AutoGenerateCode {

    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://localhost:3306/my_marketing_activity?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
            "root", "1234")
            //数据库查询
            .dbQuery(new MySqlQuery())
            //数据库schema(部分数据库适用)
            .schema("mybatis-plus")
            //数据库类型转换器
            .typeConvert(new MySqlTypeConvert())
            //数据库关键字处理器
            .keyWordsHandler(new MySqlKeyWordsHandler());

    public static void main(String[] args) {
        generateByTables("d:/data","yyz", "com.marketing.activity",
                "voucher_info");
//                "voucher_info","voucher_activity_info","voucher_activity_relation","user_coupon_info");
    }

    /**
     * 代码生成器
     * @param outputDir 文件输出地址
     * @param author 作者
     * @param packageName 包路径
     * @param tableNames 表名
     */
    private static void generateByTables(String outputDir, String author, String packageName, String... tableNames) {
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                //全局配置
                .globalConfig( builder -> builder
                        //覆盖已生成文件，默认值: false
                        .fileOverride()
                        //指定输出目录，默认值: windows:D://
                        .outputDir(outputDir)
                        //作者名，默认值: 作者
                        .author(author)
                        //时间策略，DateType.ONLY_DATE 默认值：DateType.TIME_PACK
                        .dateType(DateType.TIME_PACK)
                        //注释日期，默认值：yyyy-MM-dd
                        .commentDate("yyyy-MM-dd")
                        //禁止打开输出目录，默认值：true
                        //.disableOpenDir()
                        //开启kotlin模式，默认值：false
                        //.enableKotlin()
                        //开启swagger模式，默认值：false
                        .enableSwagger()
                        //加入构建队列
                        .build()

                )
                //包配置
                .packageConfig( builder -> builder
                        //父报名
                        .parent(packageName)
                        //父包模块名
                        //.moduleName("sys")
                        //entity包名
                        .entity("entity")
                        //service包名
                        .service("service")
                        //serviceImpl包名
                        .serviceImpl("service.impl")
                        //mapper包名
                        .mapper("mapper")
                        //mapper xml包名
                        .xml("mapper.xml")
                        //controller 包ming
                        .controller("controller")
                        //自定义文件包名,	输出自定义文件时所用到的包名
                        //.other("other")
                        //路径配置信息，Collections.singletonMap(OutputFile.mapperXml, "D://")
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "d:/data/xml"))
                        //加入构建队列
                        .build()
                )
                //模板配置
                .templateConfig( builder -> builder
                        //禁用所有模板
                        //.disable()
                        // 设置实体模板路径(kotlin)，/templates/entity.java
                        .entityKt("/templates/entity.java")
                        //禁用模板 TemplateType.ENTITY
                        .disable(TemplateType.ENTITY)
                        //设置实体模板路径(JAVA)，/templates/entity.java
                        .entity("/templates/entity.java")
                        //设置 service 模板路径，/templates/service.java
                        .service("/templates/service.java")
                        //设置 serviceImpl 模板路径，/templates/serviceImpl.java
                        .serviceImpl("/templates/serviceImpl.java")
                        //设置 mapper 模板路径，/templates/mapper.java
                        .mapper("/templates/mapper.java")
                        //设置 mapperXml 模板路径，/templates/mapper.xml
                        .mapperXml("/templates/mapper.xml")
                        //设置 controller 模板路径，/templates/controller.java
                        .controller("/templates/controller.java")
                        //加入构建队列
                        .build()
                )
                //注入配置————自定义模板
                .injectionConfig(builder -> builder
                        //输出文件之前消费者
                        .beforeOutputFile((tableInfo, objectMap) -> {
                            System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
                        })
                        //自定义配置 Map 对象
                        .customMap(Collections.singletonMap("my_field", "你好！这是我自己注入的属性哦"))
                        //自定义配置模板文件
                        //.customFile(Collections.singletonMap("test.txt", "/templates/test.vm"))
                        //加入构建队列
                        .build()
                )
                // 策略配置
                /**
                 * schema：在数据库中表示的是数据库对象集合，它包含了各种对像，比如：表，视图，存储过程，索引等等。
                 * 一般情况下一个用户对应一个集合，所以为了区分不同的集合就需要给不同的集合起名字。
                 * 用户的schema名就相当于用户名，并作为该用户缺省schema。所以说schema集合看上去像用户名。
                 * 例如当我们访问一个数据表时，如果该表没有指明属于哪个schema，系统就会自动的加上缺省的schema。
                 */
                .strategyConfig(builder -> builder
                        /** 基本参数配置 */
                        .enableCapitalMode()//开启大写命名，默认值:false
                        .enableSkipView()//开启跳过视图，默认值:false
                        .disableSqlFilter()//禁用 sql 过滤，默认值:true，语法不能支持使用 sql 过滤表的话，可以考虑关闭此开关
                        .likeTable(new LikeTable("t_user_login"))//模糊表匹配(sql 过滤)	likeTable 与 notLikeTable 只能配置一项
                        /** 设置需要生成的表名 */
                        .addInclude(tableNames)// 增加表匹配(内存过滤)，include 与 exclude 只能配置一项
                        /** 前缀配置*/
                        .addTablePrefix("t_")//增加过滤表前缀
                        //.addFieldSuffix("_")//增加过滤表后缀
                        //.addFieldPrefix("ul_")//增加过滤字段前缀  本人不建议使用，去掉后缀，会导致驼峰命名实体类的变量名不带前缀，去掉后,错误：Username，正确:ulUsername
                        //.addFieldSuffix("_")//增加过滤字段后缀

                        /** 实体策略配置 */
                        .entityBuilder()//实体策略配置
                        .disableSerialVersionUID()//禁用生成 serialVersionUID，默认值:true
                        .enableLombok()//开启 lombok 模型，默认值:false
                        .enableChainModel()//开启链式模型，默认值:false
                        .enableRemoveIsPrefix()//开启 Boolean 类型字段移除 is 前缀，默认值:false
                        .enableTableFieldAnnotation()//开启生成实体时生成字段注解，默认值:false
                        .enableActiveRecord()//开启 ActiveRecord 模型，默认值:false
                        .naming(NamingStrategy.underline_to_camel)//数据库表映射到实体的命名策略，默认下划线转驼峰命名:NamingStrategy.underline_to_camel
                        .columnNaming(NamingStrategy.underline_to_camel)//数据库表字段映射到实体属性的命名策略，默认为 null，未指定按照 naming 执行
                        .idType(IdType.AUTO)//全局主键类型
                        //.formatFileName("%sBean")//格式化文件名称，生成实体的后缀，建议这样使用，生成后：UserLoginBean
                        //.superClass(BaseEntity.class)//设置父类
                        //.enableColumnConstant()//开启生成字段常量，默认值:false
                        //.addIgnoreColumns("age")//添加忽略字段
                        //.nameConvert(INameConvert)//名称转换实现
                        //.versionColumnName("version")//乐观锁字段名(数据库)
                        //.versionPropertyName("version")//乐观锁属性名(实体)
                        //.logicDeleteColumnName("deleted")//逻辑删除字段名(数据库)
                        //.logicDeletePropertyName("deleteFlag")//逻辑删除属性名(实体)
                        //.enableSchema()//启用 schema，默认值:false，多 schema 场景的时候打开
                        //.addExclude("t_simple")//增加表排除匹配(内存过滤)，include 与 exclude 只能配置一项
                        //.notLikeTable(new LikeTable("USER"))//模糊表排除(sql 过滤)	likeTable 与 notLikeTable 只能配置一项

                        /** controller 策略配置 */
                        .controllerBuilder()//controller 策略配置
                        .enableHyphenStyle()//开启驼峰转连字符，默认值:false
                        .enableRestStyle()//开启生成@RestController 控制器，默认值:false
                        .formatFileName("%sController")//格式化文件名称——controller包下自动生成的类后缀，例如UserLoginController

                        /** Service 策略配置 */
                        .serviceBuilder()//service 策略配置
                        .formatServiceFileName("%sService")//转换 service 接口文件名称，例如：UserLoginService
                        .formatServiceImplFileName("%sServiceImpl")//转换 service 实现类文件名称，例如：UserLoginServiceImpl

                        /** mapper 策略配置 */
                        .mapperBuilder()//mapper 策略配置
                        .superClass(BaseMapper.class)//设置父类，BaseMapper是com.baomidou.mybatisplus.core.mapper中的
                        .enableMapperAnnotation()//开启 @Mapper 注解，默认值:false
                        .enableBaseResultMap()//启用 BaseResultMap 生成，默认值:false
                        .enableBaseColumnList()//启用 BaseColumnList，默认值:false
                        .formatMapperFileName("%sMapper")//转换 mapper 接口文件名称后缀，mapper目录下的，例如：UserLoginMapper（有@Mapper）
                        .formatXmlFileName("%sMapper")//转换 xml 文件名称后缀，例如：UserLoginMapper.xml，Mybatis的xml映射文件
                        .build()//加入构建队列
                )
                /**
                 * 模板引擎
                 */
                //Velocity
//                .templateEngine(new VelocityTemplateEngine())
                //Beetl
                //.templateEngine(new BeetlTemplateEngine())
                //Freemarker
                .templateEngine(new FreemarkerTemplateEngine())
                //开始自动生成代码,执行队列构建操作
                .execute();
    }
}
