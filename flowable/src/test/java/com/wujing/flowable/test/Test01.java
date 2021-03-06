package com.wujing.flowable.test;

import org.flowable.engine.*;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

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

    /**
     * 查询流程
     */
    @Test
    public void testQueryDeploy() {
        ProcessEngine processEngine = configuration.buildProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId("1")  // act_re_deployment 表 id
                .singleResult();
        System.out.println(processDefinition.getDeploymentId());
        System.out.println(processDefinition.getName());
        System.out.println(processDefinition.getDescription());
        System.out.println(processDefinition.getId());
    }

    /**
     * 删除流程
     * 删除部署的流程: repositoryService.deleteDeployment:
     * 第一个参数是 ID, 如果部署的流程启动了, 则不能再删除
     * 第二个参数是级联删除, 如果流程启动了也会删除,相关的任务也会一并删除掉
     */
    @Test
    public void testDeleteDeploy() {
        ProcessEngine processEngine = configuration.buildProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.deleteDeployment("1", true);
    }

    /**
     * 启动流程
     */
    @Test
    public void testRunProcess() {
        ProcessEngine processEngine = configuration.buildProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 构建流程变量
        HashMap<String, Object> variable = new HashMap<>();
        variable.put("employee", "张三");
        variable.put("number", 3);
        variable.put("description", "休假");
        // 启动流程实例
        ProcessInstance holidayRequest = runtimeService.startProcessInstanceByKey("holidayRequest", variable);
        System.out.println(holidayRequest.getProcessDefinitionId());
        System.out.println(holidayRequest.getActivityId());
        System.out.println(holidayRequest.getId());
    }

    /**
     * 测试任务查询
     */
    @Test
    public void testQueryTask() {
        ProcessEngine processEngine = configuration.buildProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey("holidayRequest")
                .taskAssignee("zhangsan")
                .list();
        list.forEach(task -> {
            System.out.println(task.getProcessDefinitionId());
            System.out.println(task.getDescription());
            System.out.println(task.getName());
            System.out.println(task.getDelegationState());
        });
    }



}
