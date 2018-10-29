package com.asiainfo.activiti.web.controller;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/activiti")
public class ActivitiController {
	
	@Autowired
	private ProcessEngineConfiguration processEngineConfiguration;
	
	@Autowired
	private ProcessEngine processEngine;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;

	@GetMapping("/hello")
	public String hello() {
		log.info(processEngine.getName());
		ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
		processEngine.getRepositoryService().createDeployment().addClasspathResource("process/ReqProcess.bpmn").deploy();
		processEngine.getRuntimeService().startProcessInstanceByKey("ReqProcess");
		return "Welcome to reactive world ~";
	}

	@GetMapping("/deployment")
	public String deployment(@RequestParam("path") String path) {
		log.info(processEngine.getName());
		repositoryService.createDeployment().addClasspathResource(path).deploy();
		// 关闭流程文件{格式}校验
		//repositoryService.createDeployment().disableSchemaValidation();
		// 关闭流程文件{流程错误}校验
		//repositoryService.createDeployment().disableBpmnValidation();
		return "部署成功: " + path;
	}

	@GetMapping("/launch")
	public String launch(@RequestParam("key") String key) {
		log.info(processEngine.getName());
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key);
		return "流程启动成功: " + key + ", processInstanceId: " + processInstance.getId();
	}
	
	@GetMapping("/query")
	public String query(@RequestParam("id") String processInstanceId) {
		log.info(processEngine.getName());
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
		return "流程查询成功: " + processInstanceId + ", 当前节点任务名称是: " + task.getName() + ", 当前节点任务主键是: " + task.getId();
	}
	
	@GetMapping("/complate")
	public String complate(@RequestParam("id") String taskId) {
		log.info(processEngine.getName());
		taskService.complete(taskId);
		return "流程处理成功: " + taskId;
	}
}
