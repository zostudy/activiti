package com.asiainfo.activiti.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.asiainfo.activiti.bo.WorkflowTemplateBO;
import com.asiainfo.activiti.bo.WorkflowTemplateQueryPageBO;
import com.asiainfo.activiti.common.Page;
import com.asiainfo.activiti.domain.BOWorkflowTemplate;
import com.asiainfo.activiti.dto.WorkflowTemplateDTO;
import com.asiainfo.activiti.dto.WorkflowTemplateQueryPageDTO;

/**
 * 
 * 
 * @author joker
 */
@Mapper
public interface WorkflowTemplateMapper {
	
	WorkflowTemplateMapper INSTANCE = Mappers.getMapper(WorkflowTemplateMapper.class);

	public WorkflowTemplateDTO boToDto(WorkflowTemplateBO workflowTemplateBO);
	
	public List<WorkflowTemplateDTO> boToDto(List<WorkflowTemplateBO> workflowTemplateBOs);
	
	public Page<WorkflowTemplateDTO> boToDto(Page<WorkflowTemplateBO> workflowTemplateBOs);

	@Mappings({
		@Mapping(source = "template_id_begin", target = "templateIdBegin"),
		@Mapping(source = "template_id_end", target = "templateIdEnd"),
		@Mapping(source = "process_key", target = "processKey"),
		@Mapping(source = "obj_type", target = "objType"),
		@Mapping(source = "process_name", target = "processName"),
        @Mapping(source = "page_no", target = "pageNo"),
        @Mapping(source = "page_size", target = "pageSize")
    })
	public WorkflowTemplateQueryPageBO dtoToBo(WorkflowTemplateQueryPageDTO workflowTemplateQueryPageDTO);
	
	public WorkflowTemplateBO dtoToBo(WorkflowTemplateDTO workflowTemplateDTO);

	public BOWorkflowTemplate boToDomain(WorkflowTemplateBO workflowTemplateBO);

	public WorkflowTemplateBO domainToBo(BOWorkflowTemplate boWorkflowTemplate);
	
	public List<WorkflowTemplateBO> domainToBo(List<BOWorkflowTemplate> boWorkflowTemplates);
	
	public Page<WorkflowTemplateBO> domainToBo(Page<BOWorkflowTemplate> boWorkflowTemplates);
}