//package com.example.springboot_shiro20200929.mybatisplusutils;
//
//
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.config.GlobalConfig;
//import com.baomidou.mybatisplus.generator.config.TemplateConfig;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created with IntelliJ IDEA.
// *
// * @Auther: 王飞
// * @Date: 2020/10/22/15:50
// * @Description:
// */
//public class GenerateCode {
//
//
//    public void t1(){
//
//        //配置 GlobalConfig
//        GlobalConfig globalConfig = new GlobalConfig();
//        globalConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
//        globalConfig.setAuthor("jobob");
//        globalConfig.setOpen(false);
//        //配置 DataSourceConfig
//        DataSourceConfig dataSourceConfig = new DataSourceConfig();
//        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/ant?useUnicode=true&useSSL=false&characterEncoding=utf8");
//        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
//        dataSourceConfig.setUsername("root");
//        dataSourceConfig.setPassword("password");
//
//        //指定自定义模板路径, 位置：/resources/templates/entity2.java.ftl(或者是.vm)
//        //注意不要带上.ftl(或者是.vm), 会根据使用的模板引擎自动识别
//        TemplateConfig templateConfig = new TemplateConfig().setEntity("templates/entity2.java");
//
//        AutoGenerator mpg = new AutoGenerator();
//        //配置自定义模板
//        mpg.setTemplate(templateConfig);
//
//        InjectionConfig injectionConfig = new InjectionConfig() {
//            //自定义属性注入:abc
//            //在.ftl(或者是.vm)模板中，通过${cfg.abc}获取属性
//            @Override
//            public void initMap() {
//                Map<String, Object> map = new HashMap<>();
//                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
//                this.setMap(map);
//            }
//        };
////        AutoGenerator mpg = new AutoGenerator();
////        //配置自定义属性注入
////        mpg.setCfg(injectionConfig);
//
//
//
//    }
//
//
//
//}
