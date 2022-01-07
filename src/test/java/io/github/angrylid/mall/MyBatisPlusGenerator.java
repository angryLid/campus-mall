package io.github.angrylid.mall;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;

import java.util.Collections;

public class MyBatisPlusGenerator {
    public static String projectPath = System.getProperty("user.dir");

    public static void main(String[] args) {
        FastAutoGenerator.create(
                "jdbc:mysql://localhost:3306/scott?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai",
                "root", "")
                .globalConfig(builder -> {
                    builder.author("angrylid") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(projectPath + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("io.github.angrylid.mall") // 设置父包名
                            .moduleName("generated")// 设置父包模块名
                            .entity("entity")
                            .mapper("mapper")
                            // .service("service")
                            // .serviceImpl("service.impl")
                            // .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,
                                    projectPath + "/src/main/resources/io/github/angrylid/mall/generated/mapper/")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user")
                            .addInclude("product")// 设置需要生成的表名
                            .addInclude("product_image")
                            .addInclude("relation")
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateConfig(builder -> builder.disable(TemplateType.SERVICE, TemplateType.SERVICEIMPL,
                        TemplateType.CONTROLLER, TemplateType.XML).build())
                // .templateEngine(new FreemarkerTemplateEngine())
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}