package com.asiainfo.activiti.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.activiti.bo.WorkflowTemplateBO;
import com.asiainfo.activiti.bo.WorkflowTemplateQueryPageBO;
import com.asiainfo.activiti.common.Page;
import com.asiainfo.activiti.dao.CommonDAO;
import com.asiainfo.activiti.domain.BOWorkflowTemplate;
import com.asiainfo.activiti.mapper.WorkflowTemplateMapper;
import com.asiainfo.activiti.service.IWorkflowTemplateService;

/**
 * 
 * 
 * @author joker
 */
@Service("workflowTemplateService")
@Transactional(rollbackOn = Exception.class)
public class WorkflowTemplateServiceImpl implements IWorkflowTemplateService {
	
	@Autowired
	private CommonDAO commonDAO;
	
	@Override
	public WorkflowTemplateBO save(WorkflowTemplateBO workflowTemplateBO) throws Exception {
		BOWorkflowTemplate boWorkflowTemplate = WorkflowTemplateMapper.INSTANCE.boToDomain(workflowTemplateBO);
		boWorkflowTemplate = commonDAO.saveOrUpdate(boWorkflowTemplate, BOWorkflowTemplate.class);
		return WorkflowTemplateMapper.INSTANCE.domainToBo(boWorkflowTemplate);
	}
	
	@Override
	public WorkflowTemplateBO findByPrimaryKey(java.lang.Long templateId) throws Exception {
		BOWorkflowTemplate boWorkflowTemplate = commonDAO.findById(BOWorkflowTemplate.class, templateId);
		return WorkflowTemplateMapper.INSTANCE.domainToBo(boWorkflowTemplate);
	}
	
	@Override
	public WorkflowTemplateBO update(WorkflowTemplateBO workflowTemplateBO) throws Exception {
		BOWorkflowTemplate boWorkflowTemplate = WorkflowTemplateMapper.INSTANCE.boToDomain(workflowTemplateBO);
		boWorkflowTemplate = commonDAO.saveOrUpdate(boWorkflowTemplate, BOWorkflowTemplate.class);
		return WorkflowTemplateMapper.INSTANCE.domainToBo(boWorkflowTemplate);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Page<WorkflowTemplateBO> findByConds(WorkflowTemplateQueryPageBO workflowTemplateQueryPageBO) throws Exception {
		StringBuffer hql = new StringBuffer("SELECT o FROM BOWorkflowTemplate o WHERE 1 = 1");
		Map<String, Object> param = new HashMap<String, Object>();
	    if (workflowTemplateQueryPageBO.getTemplateIdBegin() != null) {
			hql.append(" AND o.templateId >= :templateIdBegin");
			param.put("templateIdBegin", workflowTemplateQueryPageBO.getTemplateIdBegin());
		}
	    if (workflowTemplateQueryPageBO.getTemplateIdEnd() != null) {
			hql.append(" AND o.templateId <= :templateIdEnd");
			param.put("templateIdEnd", workflowTemplateQueryPageBO.getTemplateIdEnd());
		}
	    if (!StringUtils.isBlank(workflowTemplateQueryPageBO.getProcessKey())) {
			hql.append(" AND o.processKey LIKE :processKey");
			param.put("processKey", "%"+workflowTemplateQueryPageBO.getProcessKey()+"%");
		}
	    if (!StringUtils.isBlank(workflowTemplateQueryPageBO.getObjType())) {
			hql.append(" AND o.objType LIKE :objType");
			param.put("objType", "%"+workflowTemplateQueryPageBO.getObjType()+"%");
		}
	    if (!StringUtils.isBlank(workflowTemplateQueryPageBO.getProcessName())) {
			hql.append(" AND o.processName LIKE :processName");
			param.put("processName", "%"+workflowTemplateQueryPageBO.getProcessName()+"%");
		}
		hql.append(" ORDER BY o.templateId DESC");
		List<BOWorkflowTemplate> boWorkflowTemplates = commonDAO.findByJPAQL(hql.toString(), param);
		Page<WorkflowTemplateBO> page = new Page<WorkflowTemplateBO>();
		if ((workflowTemplateQueryPageBO.getPageNo() != null && workflowTemplateQueryPageBO.getPageNo().compareTo(0) > 0) && (workflowTemplateQueryPageBO.getPageSize() != null && workflowTemplateQueryPageBO.getPageSize().compareTo(0) > 0)) {
			if (boWorkflowTemplates.isEmpty()) {
				return page;
			}
			page.setRowCount(boWorkflowTemplates.size());
			boWorkflowTemplates = commonDAO.findByJPAQL(hql.toString(), param, workflowTemplateQueryPageBO.getPageNo(), workflowTemplateQueryPageBO.getPageSize());
			page.generatePageCount(workflowTemplateQueryPageBO.getPageSize());
		}
		page.setPageData(WorkflowTemplateMapper.INSTANCE.domainToBo(boWorkflowTemplates));
		return page;
	}
	
}