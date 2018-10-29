package com.asiainfo.activiti.service;

import com.asiainfo.activiti.bo.WorkflowTemplateBO;
import com.asiainfo.activiti.bo.WorkflowTemplateQueryPageBO;
import com.asiainfo.activiti.common.Page;

/**
 * 
 * 
 * @author joker
 */
public interface IWorkflowTemplateService {
	
	public WorkflowTemplateBO save(WorkflowTemplateBO workflowTemplateBO) throws Exception;
	
	public WorkflowTemplateBO findByPrimaryKey(java.lang.Long templateId) throws Exception;
	
	public WorkflowTemplateBO update(WorkflowTemplateBO workflowTemplateBO) throws Exception;
	
	public Page<WorkflowTemplateBO> findByConds(WorkflowTemplateQueryPageBO workflowTemplateQueryPageBO) throws Exception;
	
}