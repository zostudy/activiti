package com.asiainfo.activiti.web.controller;

import java.util.UUID;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {

	
	@Autowired
	private ProcessEngineConfiguration processEngineConfiguration;
	
	@Autowired
	private ProcessEngine processEngine;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private IdentityService identityService;

	@GetMapping("/hello")
	public String hello() {
		log.info(processEngine.getName());
		ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
		processEngine.getRepositoryService().createDeployment().addClasspathResource("process/ReqProcess.bpmn").deploy();
		processEngine.getRuntimeService().startProcessInstanceByKey("ReqProcess");
		return "Welcome to reactive world ~";
	}

	@GetMapping("/auth")
	public String auth() {
		log.info(processEngine.getName());
		Task task = taskService.newTask(UUID.randomUUID().toString());
		task.setName("ceshirenwu");
		taskService.saveTask(task);
		
		Group group = identityService.newGroup(UUID.randomUUID().toString());
		group.setName("用户组");
		identityService.saveGroup(group);
		// 候选用户组
		taskService.addCandidateGroup(task.getId(), group.getId());
		
		User user = identityService.newUser(UUID.randomUUID().toString());
		user.setFirstName("用户");
		identityService.saveUser(user);
		// 候选用户
		taskService.addCandidateUser(task.getId(), user.getId());
		
		// 待办任务
		// List<Task> tasks = taskService.createTaskQuery().taskCandidateUser(user.getId()).list();
		
		return "Welcome to reactive world ~";
		
		
	}
}
