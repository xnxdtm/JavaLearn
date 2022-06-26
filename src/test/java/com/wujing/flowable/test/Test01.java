package com.wujing.flowable.test;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.junit.Before;
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

    ProcessEngineConfiguration configuration = null;
    @Before
    public void before() {
        // 获取流程引擎对象ProcessEngineConfiguration
        configuration = new StandaloneProcessEngineConfiguration();
        configuration.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        // nullCatalogMeansCurrent=true解决数据库中已经存在的库中有 flowable 表,所以创建失败的问题
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/mca_flowable?serverTimezone=UTC&nullCatalogMeansCurrent=true");
        configuration.setJdbcUsername("flowable");
        configuration.setJdbcPassword("flowable");
        // 如果表结构不存在就新建表结构
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine engine = configuration.buildProcessEngine();
    }

    /**
     * 部署流程
     */
    @Test
    public void testDeploy() {
        // 获取 engine 及 repositoryService 对象
        ProcessEngine processEngine = configuration.buildProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 关联要部署的流程文件
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("holiday-request.bpmn20.xml")
                .name("请假流程")
                .deploy();
        System.out.println(deploy.getId());
        System.out.println(deploy.getName());
    }



}
