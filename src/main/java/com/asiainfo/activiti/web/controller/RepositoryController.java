package com.asiainfo.activiti.web.controller;

import java.util.Collection;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.ExclusiveGateway;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.ParallelGateway;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.util.io.ResourceStreamSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/repository")
public class RepositoryController {

	@Autowired
	private ProcessEngineConfiguration processEngineConfiguration;
	
	@Autowired
	private ProcessEngine processEngine;

	@GetMapping("/hello")
	public String hello() {
		log.info(processEngine.getName());
		ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
		processEngine.getRepositoryService().createDeployment().addClasspathResource("process/ReqProcess.bpmn").deploy();
		processEngine.getRuntimeService().startProcessInstanceByKey("ReqProcess");
		return "Welcome to reactive world ~";
	}

	@GetMapping("/analysis")
	public String analysis() {
		log.info(processEngine.getName());
		ResourceStreamSource resourceStreamSource = new ResourceStreamSource("process/ComputerProcess.bpmn");
		
		BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
		BpmnModel bpmnModel = bpmnXMLConverter.convertToBpmnModel(resourceStreamSource, true, true);
		org.activiti.bpmn.model.Process mainProcess = bpmnModel.getMainProcess();
		Collection<FlowElement> flowElements = mainProcess.getFlowElements();
		flowElements.forEach(flowElement -> {
			if (flowElement instanceof SequenceFlow) {
				log.info("线: {主键: "+flowElement.getId()+", 名称: "+flowElement.getName()+"}");
				log.info(((SequenceFlow) flowElement).getSourceRef());
				log.info(((SequenceFlow) flowElement).getTargetRef());
			} else if (flowElement instanceof StartEvent) {
				log.info("开始: {主键: "+flowElement.getId()+", 名称: "+flowElement.getName()+"}");
			} else if (flowElement instanceof EndEvent) {
				log.info("结束: {主键: "+flowElement.getId()+", 名称: "+flowElement.getName()+"}");
			} else if (flowElement instanceof UserTask) {
				log.info("人工: {主键: "+flowElement.getId()+", 名称: "+flowElement.getName()+"}");
			} else if (flowElement instanceof ExclusiveGateway) {
				log.info("排他: {主键: "+flowElement.getId()+", 名称: "+flowElement.getName()+"}");
			} else if (flowElement instanceof ParallelGateway) {
				log.info("并行: {主键: "+flowElement.getId()+", 名称: "+flowElement.getName()+"}");
			} else {
				log.info("其他: {主键: "+flowElement.getId()+", 名称: "+flowElement.getName()+"}");
			}
		});
		
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		String result = null;
		try {
			result = objectMapper.writeValueAsString(bpmnModel);
			//log.info(objectMapper.writeValueAsString(bpmnModel));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		//log.info(bpmnModel.toString());
		return result;
	}
}
