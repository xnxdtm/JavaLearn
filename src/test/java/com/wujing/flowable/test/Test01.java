package com.wujing.flowable.test;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.junit.Test;

/**
 * @author zhangjq
 * @version V1.0
 * @Description Test01
 */
public class Test01 {

    /**
     * 构建流程引擎对象
     */
    @Test
    public void testProcessEngine() {
        // 获取流程引擎对象ProcessEngineConfiguration
        ProcessEngineConfiguration config = new StandaloneProcessEngineConfiguration();
        config.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        // nullCatalogMeansCurrent=true解决数据库中已经存在的库中有 flowable 表,所以创建失败的问题
        config.setJdbcUrl("jdbc:mysql://localhost:3306/mca_flowable?serverTimezone=UTC&nullCatalogMeansCurrent=true");
        config.setJdbcUsername("flowable");
        config.setJdbcPassword("flowable");
        // 如果表结构不存在就新建表结构
        config.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine engine = config.buildProcessEngine();
    }
}
