package com.asiainfo.activiti.web.controller;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/query")
public class QueryController {
	
	/*@Autowired
	private ProcessEngineConfiguration processEngineConfiguration;*/
	
	@Autowired
	private ProcessEngine processEngine;
	
	/*@Autowired
	private RepositoryService repositoryService;*/
	
	/*@Autowired
	private RuntimeService runtimeService;*/
	
	@Autowired
	private IdentityService identityService;
	
	/*@Autowired
	private TaskService taskService;*/
	
	@GetMapping("/id")
	public String hello() {
		log.info(processEngine.getName());
		identityService.createNativeUserQuery().sql("SELECT * FROM ACT_ID_USER WHERE FIRST_ = #{first}").parameter("first", "wang").count();
		return "Welcome to reactive world ~";
	}

}
