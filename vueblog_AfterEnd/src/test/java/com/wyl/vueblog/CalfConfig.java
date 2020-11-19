package com.wyl.vueblog;/**
 * @Auther:小王
 * @Date:2020/9/5
 * @Description:mybatis-plus
 * @version:1.0
 */

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 **/
//代码自动生成器
public class CalfConfig {
    public static void main(String[] args) {
        //需要构建一个代码自动生成器对象
        AutoGenerator mpg = new AutoGenerator();
        //配置策略
        //1，全局配置
        mpg.setTemplateEngine(new FreemarkerTemplateEngine()); // 选择 freemarker 引擎，默认 Veloctiy
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java");//System.getProperty("user.dir"):获取系统中当前目录
        globalConfig.setAuthor("calf");
        globalConfig.setOpen(false);//是否打开资源管理器
        globalConfig.setFileOverride(false);//是否覆盖原来生成的
//        globalConfig.setMapperName("%sMapper");
//        globalConfig.setXmlName("%sMapper");
        globalConfig.setServiceName("%sService");//去service的I前缀
//        globalConfig.setServiceImplName("%sServiceImpl");
//        globalConfig.setControllerName("%sController");
        globalConfig.setIdType(IdType.ID_WORKER);//主键生成类型
        globalConfig.setDateType(DateType.ONLY_DATE);//时间
        mpg.setGlobalConfig(globalConfig);

        //1,设置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/vueblog?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        dataSourceConfig.setDbType(DbType.MYSQL);
        mpg.setDataSource(dataSourceConfig);

        //3，包的配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName("calf");
        pc.setParent("com.wyl.vueblog");
        pc.setEntity("pojo");
        pc.setMapper("mapper");
        pc.setController("controller");
        pc.setService("service");
        mpg.setPackageInfo(pc);
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {

            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/com/wyl/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        // 配置模板
//        TemplateConfig templateConfig = new TemplateConfig();
//
//        // 配置自定义输出模板
//        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
//         templateConfig.setEntity("templates/entity.java");
//        // templateConfig.setService();
//        // templateConfig.setController();

//        templateConfig.setXml(null);
//        mpg.setTemplate(templateConfig);

        //4,策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("m_user");
        strategy.setTablePrefix("m_");
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);

        //自动填充的配置
//        TableFill create_time = new TableFill("create_time", FieldFill.INSERT);//设置时的生成策略
//        TableFill update_time = new TableFill("update_time", FieldFill.INSERT_UPDATE);//设置更新时间的生成策略
//        ArrayList<TableFill> list = new ArrayList<>();
//        list.add(create_time);
//        list.add(update_time);
//        strategy.setTableFillList(list);

        //乐观锁
//        strategy.setVersionFieldName("version");
        strategy.setRestControllerStyle(true);//开启驼峰命名
//        strategy.setControllerMappingHyphenStyle(true);//开启链接地址的下划线命名 localhost:8080/hello_id_2
        mpg.setStrategy(strategy);



        //执行
        mpg.execute();
    }

}
