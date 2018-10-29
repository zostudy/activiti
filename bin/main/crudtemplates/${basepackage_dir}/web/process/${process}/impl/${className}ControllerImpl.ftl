<#assign className = table.className>
<#assign tableRemarks = table.remarks?default("")>
<#assign hasDateTimeColumn = table.hasDateTimeColumn>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.web.process.${process}.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.rms.core.api.Page;
import ${basepackage}.bo.process.${process}.${className}BO;
import ${basepackage}.dto.common.ExecuteResultDTO;
import ${basepackage}.dto.process.${process}.${className}DTO;
import ${basepackage}.dto.process.${process}.${className}QueryPageDTO;
import ${basepackage}.mapper.process.${process}.${className}Mapper;
import ${basepackage}.service.process.${process}.I${className}Service;
import ${basepackage}.web.process.${process}.I${className}Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * ${tableRemarks}
 * 
 * @author joker
 */
@Data
@Log4j
@Transactional
@RestController
@AllArgsConstructor
@RequiredArgsConstructor
@Api(value = "${tableRemarks}增删改查操作接口(流程无关)")
public class ${className}ControllerImpl implements I${className}Controller {
	
	@Autowired
	private I${className}Service ${classNameLower}Service;

	@Override
	@ApiOperation(value = "依据主键删除${tableRemarks}(流程无关)")
	<#if (table.pkColumn)??>
	public void deleteByPrimaryKey(@PathVariable ${table.pkColumn.javaType} ${table.pkColumn.columnNameLower}) throws Exception {
		${classNameLower}Service.deleteByPrimaryKey(${table.pkColumn.columnNameLower});
	<#else>
	<#list table.columns as column>
    	<#if table.pkCount==0 && column_index==0>
	public void deleteByPrimaryKey(@PathVariable ${column.javaType} ${column.columnNameLower}) throws Exception {
		${classNameLower}Service.deleteByPrimaryKey(${column.columnNameLower});
		</#if>
	</#list>
	</#if>
	}
	
	@Override
	@ApiOperation(value = "新增${tableRemarks}(流程无关)")
	public ExecuteResultDTO save(@RequestBody ${className}DTO ${classNameLower}DTO) throws Exception {
		ExecuteResultDTO result = new ExecuteResultDTO();
		try {
			${classNameLower}DTO = ${className}Mapper.INSTANCE.boToDto(${classNameLower}Service.save(${className}Mapper.INSTANCE.dtoToBo(${classNameLower}DTO)));
			result.setCode(1);
			result.setMsg("success");
			Map<String, Long> primaryKey = new HashMap<String, Long>();
			<#if (table.pkColumn)??>
			primaryKey.put("${table.pkColumn.sqlName?lower_case}", ${classNameLower}DTO.get${table.pkColumn.columnName}());
			<#else>
			<#list table.columns as column>
		    	<#if table.pkCount==0 && column_index==0>
			primaryKey.put("${column.sqlName?lower_case}", ${classNameLower}DTO.get${column.columnName}());
				</#if>
			</#list>
			</#if>
			result.setObj(primaryKey);
		} catch (Exception e) {
			result.setCode(0);
			result.setMsg(e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
			log.error(e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
		}
		return result;
	}
	
	@Override
	@ApiOperation(value = "依据主键查询${tableRemarks}(流程无关)")
	<#if (table.pkColumn)??>
	public ExecuteResultDTO findByPrimaryKey(@PathVariable ${table.pkColumn.javaType} ${table.pkColumn.columnNameLower}) throws Exception {
		ExecuteResultDTO result = new ExecuteResultDTO();
		try {
			${className}DTO ${classNameLower}DTO = ${className}Mapper.INSTANCE.boToDto(${classNameLower}Service.findByPrimaryKey(${table.pkColumn.columnNameLower}));
	<#else>
	<#list table.columns as column>
    	<#if table.pkCount==0 && column_index==0>
	public ExecuteResultDTO findByPrimaryKey(@PathVariable ${column.javaType} ${column.columnNameLower}) throws Exception {
		ExecuteResultDTO result = new ExecuteResultDTO();
		try {
			${className}DTO ${classNameLower}DTO = ${className}Mapper.INSTANCE.boToDto(${classNameLower}Service.findByPrimaryKey(${column.columnNameLower}));
		</#if>
	</#list>
	</#if>
			result.setCode(1);
			result.setMsg("success");
			result.setObj(${classNameLower}DTO);
		} catch (Exception e) {
			result.setCode(0);
			result.setMsg(e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
			log.error(e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
		}
		return result;
	}
	
	@Override
	@ApiOperation(value = "更新${tableRemarks}(流程无关)")
	public ExecuteResultDTO update(@RequestBody ${className}DTO ${classNameLower}DTO) throws Exception {
		ExecuteResultDTO result = new ExecuteResultDTO();
		try {
			${classNameLower}DTO = ${className}Mapper.INSTANCE.boToDto(${classNameLower}Service.update(${className}Mapper.INSTANCE.dtoToBo(${classNameLower}DTO)));
			result.setCode(1);
			result.setMsg("success");
			Map<String, Long> primaryKey = new HashMap<String, Long>();
			<#if (table.pkColumn)??>
			primaryKey.put("${table.pkColumn.sqlName?lower_case}", ${classNameLower}DTO.get${table.pkColumn.columnName}());
			<#else>
			<#list table.columns as column>
		    	<#if table.pkCount==0 && column_index==0>
			primaryKey.put("${column.sqlName?lower_case}", ${classNameLower}DTO.get${column.columnName}());
				</#if>
			</#list>
			</#if>
			result.setObj(primaryKey);
		} catch (Exception e) {
			result.setCode(0);
			result.setMsg(e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
			log.error(e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
		}
		return result;
	}
	
	@Override
	@ApiOperation(value = "分页查询${tableRemarks}(流程无关)")
	public ExecuteResultDTO findByConds(${className}QueryPageDTO ${classNameLower}QueryPageDTO) throws Exception {
		ExecuteResultDTO result = new ExecuteResultDTO();
		try {
			Page<${className}BO> ${classNameLower}BOs = ${classNameLower}Service.findByConds(${className}Mapper.INSTANCE.dtoToBo(${classNameLower}QueryPageDTO));
			result.setCode(1);
			result.setMsg("success");
			result.setObj(${className}Mapper.INSTANCE.boToDto(${classNameLower}BOs));
		} catch (Exception e) {
			result.setCode(0);
			result.setMsg(e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
			log.error(e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
		}
		return result;
	}
	
}