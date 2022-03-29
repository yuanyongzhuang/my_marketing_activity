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
            "root", "123456")
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
//                "voucher_info");
                "voucher_info","voucher_activity_info","voucher_activity_relation","user_coupon_info");
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
                        .disableOpenDir()
                        //开启kotlin模式，默认值：false
                        //.enableKotlin()
                        //开启swagger模式，默认值：false
//                        .enableSwagger()
                        //加入构建队列
                        .build()

                )
                //包配置
                .packageConfig( builder -> builder
                        //父报名
                        .parent(packageName)
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
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml, outputDir+"/xml"))
                        //加入构建队列
                        .build()
                )
                // 策略配置
                .strategyConfig(builder -> builder
                        /** 基本参数配置 */
                        .enableCapitalMode()//开启大写命名，默认值:false
                        .enableSkipView()//开启跳过视图，默认值:false
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
                        .enableTableFieldAnnotation()//开启生成实体时生成字段注解，默认值:false
                        .naming(NamingStrategy.underline_to_camel)//数据库表映射到实体的命名策略，默认下划线转驼峰命名:NamingStrategy.underline_to_camel
                        .columnNaming(NamingStrategy.underline_to_camel)//数据库表字段映射到实体属性的命名策略，默认为 null，未指定按照 naming 执行
                        .idType(IdType.AUTO)//全局主键类型
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
                //Freemarker
                .templateEngine(new FreemarkerTemplateEngine())
                //开始自动生成代码,执行队列构建操作
                .execute();
    }
}
