package com.asiainfo.activiti.service;

import com.asiainfo.activiti.bo.WorkflowBO;
import com.asiainfo.activiti.bo.WorkflowQueryPageBO;
import com.asiainfo.activiti.common.Page;

/**
 * 流程模版
 * 
 * @author joker
 */
public interface IWorkflowService {
	
	public WorkflowBO save(WorkflowBO workflowBO) throws Exception;
	
	public WorkflowBO findByPrimaryKey(java.lang.Long wfItemId) throws Exception;
	
	public WorkflowBO update(WorkflowBO workflowBO) throws Exception;
	
	public Page<WorkflowBO> findByConds(WorkflowQueryPageBO workflowQueryPageBO) throws Exception;
	
}