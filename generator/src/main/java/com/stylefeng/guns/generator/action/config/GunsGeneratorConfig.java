package com.stylefeng.guns.generator.action.config;

import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 默认的代码生成的配置
 *
 * @author fengshuonan
 * @date 2017-10-28-下午8:27
 */
public class GunsGeneratorConfig extends AbstractGeneratorConfig {

    protected void globalConfig() {
        globalConfig.setOutputDir("/Users/apple/generator");//写自己项目的绝对路径,注意具体到java目录
        globalConfig.setFileOverride(true);
        globalConfig.setEnableCache(false);
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);
        globalConfig.setOpen(false);
        globalConfig.setAuthor("dge1992");
    }

    protected void dataSourceConfig() {
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("");
        dataSourceConfig.setPassword("");
        dataSourceConfig.setUrl("");
    }

    protected void strategyConfig() {
        strategyConfig.setTablePrefix(new String[]{"fc_"});// 此处可以修改为您的表前缀
        strategyConfig.setInclude("fc_fee_file_rel");// 数据库表
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);//表名生成策略
    }

    protected void packageConfig() {
        packageConfig.setParent(null);
        packageConfig.setEntity("com.stylefeng.guns.admin.common.persistence.model");
        packageConfig.setMapper("com.stylefeng.guns.admin.common.persistence.dao");
        packageConfig.setXml("com.stylefeng.guns.admin.common.persistence.dao.mapping");
    }

    protected void contextConfig() {
        contextConfig.setProPackage("com.stylefeng.guns.admin");
        contextConfig.setCoreBasePackage("com.stylefeng.guns.core");
        contextConfig.setBizChName("费用文件");
        contextConfig.setBizEnName("feeFileRel");
        contextConfig.setModuleName("system");
        contextConfig.setProjectPath("/Users/apple/generator");//写自己项目的绝对路径
        contextConfig.setEntityName("FeeFileRel");
        sqlConfig.setParentMenuName(null);//这里写已有菜单的名称,当做父节点

        /**
         * mybatis-plus 生成器开关
         */
        contextConfig.setEntitySwitch(true);
        contextConfig.setDaoSwitch(true);
        contextConfig.setServiceSwitch(true);

        /**
         * guns 生成器开关
         */
        contextConfig.setControllerSwitch(true);
        contextConfig.setIndexPageSwitch(true);
        contextConfig.setAddPageSwitch(true);
        contextConfig.setEditPageSwitch(true);
        contextConfig.setJsSwitch(true);
        contextConfig.setInfoJsSwitch(true);
        contextConfig.setSqlSwitch(true);
    }

    @Override
    protected void config() {
        globalConfig();
        dataSourceConfig();
        strategyConfig();
        packageConfig();
        contextConfig();
    }
}
