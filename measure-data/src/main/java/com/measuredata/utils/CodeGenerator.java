package com.measuredata.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * mybatis-plus逆向生成工具
 */
public class CodeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create(new DataSourceConfig.Builder("jdbc:mysql://localhost:3306/measure?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8&useSSL=false", "root", "123456")
//                        .schema("spring")// 设置schemaName
//                        .typeConvert(new MySqlTypeConvert())
                )
                .globalConfig(builder -> {
                    builder.author("gjmou") // 设置作者
                            .disableOpenDir()
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D://IDEAWorkSpace//simple-project//measure-data//src//test//java"); // 指定输出目录
                }).packageConfig(builder -> {
                    // 连起来就是包的根路径com.gmall.base
                    builder.parent("com") // 设置父包名
                            .moduleName("measuredata") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://IDEAWorkSpace//simple-project//measure-data//src//test//java//com//measuredata//mapper")); // 设置mapperXml生成路径
                }).strategyConfig(builder -> {
                    builder.addInclude("MEASURE_SUNSHINE") // 设置需要生成的表名
                            //.addTablePrefix("p_", "c_") // 设置过滤表前缀
                            // 实体类构造器
                            .entityBuilder().enableLombok()// 使用lombok
                            .disableSerialVersionUID()// 不自动实现序列化接口
                            .enableTableFieldAnnotation() // 添加属性的数据库字段名
                            // mapper构造器
                            .mapperBuilder()
//                            .enableBaseResultMap()// 自动生成数据库字段与实体的映射关系
                    ;
                }).templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateConfig(builder -> {
                    builder.controller("").service("").serviceImpl("")// 不需要controller和service和impl
                            .entity("/templates/entity.java");// 设置自定义模板引擎
                }).execute();

    }
}
