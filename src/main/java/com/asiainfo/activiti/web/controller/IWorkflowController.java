package com.asiainfo.activiti.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asiainfo.activiti.common.ExecuteResultDTO;
import com.asiainfo.activiti.dto.WorkflowDTO;
import com.asiainfo.activiti.dto.WorkflowQueryPageDTO;

/**
 * 流程模版
 * 
 * @author joker
 */
@RequestMapping("/process/workflow")
public interface IWorkflowController {
	
	@PostMapping
	public ExecuteResultDTO save(@RequestBody WorkflowDTO workflowDTO) throws Exception;
	
	@GetMapping(value = "/{wfItemId:\\d+}")
	public ExecuteResultDTO findByPrimaryKey(@PathVariable java.lang.Long wfItemId) throws Exception;
	
	@PutMapping
	public ExecuteResultDTO update(@RequestBody WorkflowDTO workflowDTO) throws Exception;
	
	@GetMapping
	public ExecuteResultDTO findByConds(WorkflowQueryPageDTO workflowQueryPageDTO) throws Exception;
	
}