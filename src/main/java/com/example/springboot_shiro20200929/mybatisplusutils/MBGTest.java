package com.example.springboot_shiro20200929.mybatisplusutils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 王飞
 * @Date: 2020/07/17/13:45
 * @Description:
 */
// 此处使用的默认的模板
public class MBGTest {

    @Test
    public void aa(){
         String  packagePath="com.example.springboot_shiro20200929";//包路径
//        String[] tableNames = {"admin","role","perm","admin_role","role_perm"};
        String[] tableNames = {"aa"};
        for (String tableName:tableNames) {
            this.generate(packagePath,tableName);
        }
    }

    public  void generate(String packagePath,String tableName){
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setActiveRecord(true)	// 是否支持ActiveRecord
                .setEnableCache(false)		// 是否开启二级缓存
                .setAuthor("王飞") 			// 设置作者
                .setOutputDir(projectPath + "/src/main/java") // 生成路径(相对路径)
//                .setOutputDir("C:\\Users\\EDZ\\Desktop\\生成代码文件") // 生成路径(绝对路径)
                .setFileOverride(true)  	// 文件覆盖
                .setBaseResultMap(true)		// mapper映射文件是否生成BaseResultMap
                .setBaseColumnList(true)	// XML 是否生成BaseColumn

                // 自定义文件命名，注意 %s 会自动填充表实体属性！
                .setServiceName("%sService")	// 设置生成的service接口的名字的首字母是否为I
                .setMapperName("%sMapper")
                .setXmlName("%sMapper");

        // 数据源配置
        DataSourceConfig source = new DataSourceConfig();
        source.setDbType(DbType.MYSQL)					// 设置数据库类型
                .setDriverName("com.mysql.cj.jdbc.Driver")		// 数据库驱动
                .setUrl("jdbc:mysql://localhost:3306/wf_shiro20201012?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Hongkong")	// 数据库地址
                .setUsername("root")						// 用户名
                .setPassword("123456");						// 密码

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setCapitalMode(true) 		//全局大写命名
                //.setDbColumnUnderline(true)		// 指定表名 字段名是否使用下划线
                .setNaming(NamingStrategy.underline_to_camel)	// 数据库表映射到实体的命名策略
//                .setTablePrefix("tbl_")				// 表前缀
                .setInclude(tableName); 		// 生成的表

        // 包名配置（自定义包名）
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(packagePath) 	// 所有包的父包
                .setEntity("bean")				// 实体类包
                .setMapper("mapper")			// mapper接口
                .setXml("mapper.xml")				// xml文件
                .setService("service")			// service文件
                .setServiceImpl("service.impl")// serviceImpl文件
                .setController("controller");	// controller层的包

//        TemplateConfig templateConfig = new TemplateConfig();
//        templateConfig.setService(null);
//        templateConfig.setController(null);
//        templateConfig.setServiceImpl(null);
//        templateConfig.setEntity(null);
//        templateConfig.setXml(null);
        // 整合配置
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig)
                .setDataSource(source)
                .setPackageInfo(packageConfig)
                .setStrategy(strategy);
//        autoGenerator.setTemplate(templateConfig);

        // 执行代码生成器
        autoGenerator.execute();
    }

}
