package com.asiainfo.activiti.web.controller;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/definition")
public class DefinitionController {
	
	@Autowired
	private ProcessEngine processEngine;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@GetMapping("/deploy")
	public String hello() {
		log.info(processEngine.getName());
		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
		
		deploymentBuilder = repositoryService.createDeployment();
		deploymentBuilder.addClasspathResource("process/MyProcess.bpmn").addClasspathResource("process/MyProcess.png");
		return "Welcome to reactive world ~";
	}
}
