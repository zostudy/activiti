package com.asiainfo.activiti.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asiainfo.activiti.common.ExecuteResultDTO;
import com.asiainfo.activiti.dto.WorkflowTemplateDTO;
import com.asiainfo.activiti.dto.WorkflowTemplateQueryPageDTO;

/**
 * 
 * 
 * @author joker
 */
@RequestMapping("/process/expenses/workflowtemplate")
public interface IWorkflowTemplateController {
	
	@PostMapping
	public ExecuteResultDTO save(@RequestBody WorkflowTemplateDTO workflowTemplateDTO) throws Exception;
	
	@GetMapping(value = "/{templateId:\\d+}")
	public ExecuteResultDTO findByPrimaryKey(@PathVariable java.lang.Long templateId) throws Exception;
	
	@PutMapping
	public ExecuteResultDTO update(@RequestBody WorkflowTemplateDTO workflowTemplateDTO) throws Exception;
	
	@GetMapping
	public ExecuteResultDTO findByConds(WorkflowTemplateQueryPageDTO workflowTemplateQueryPageDTO) throws Exception;
	
}