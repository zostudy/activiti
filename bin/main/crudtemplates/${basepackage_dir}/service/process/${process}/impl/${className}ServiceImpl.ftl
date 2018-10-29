<#assign className = table.className>
<#assign tableRemarks = table.remarks?default("")>
<#assign hasDateTimeColumn = table.hasDateTimeColumn>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.service.process.${process}.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.rms.core.api.Page;
import ${basepackage}.bo.process.${process}.${className}BO;
import ${basepackage}.bo.process.${process}.${className}QueryPageBO;
import ${basepackage}.dao.CommonDAO;
import ${basepackage}.domain.${process}.BO${className};
import ${basepackage}.mapper.process.${process}.${className}Mapper;
import ${basepackage}.service.process.${process}.I${className}Service;

/**
 * ${tableRemarks}
 * 
 * @author joker
 */
@Service("${classNameLower}Service")
@Transactional(rollbackOn = Exception.class)
public class ${className}ServiceImpl implements I${className}Service {
	
	@Autowired
	private CommonDAO commonDAO;
	
	@Override
	<#if (table.pkColumn)??>
	public void deleteByPrimaryKey(${table.pkColumn.javaType} ${table.pkColumn.columnNameLower}) throws Exception {
		commonDAO.delete(${table.pkColumn.columnNameLower}, BO${className}.class);
	<#else>
	<#list table.columns as column>
    	<#if table.pkCount==0 && column_index==0>
	public void deleteByPrimaryKey(${column.javaType} ${column.columnNameLower}) throws Exception {
		commonDAO.delete(${column.columnNameLower}, BO${className}.class);
		</#if>
	</#list>
	</#if>
	}
	
	@Override
	public ${className}BO save(${className}BO ${classNameLower}BO) throws Exception {
		BO${className} bo${className} = ${className}Mapper.INSTANCE.boToDomain(${classNameLower}BO);
		bo${className} = commonDAO.saveOrUpdate(bo${className}, BO${className}.class);
		return ${className}Mapper.INSTANCE.domainToBo(bo${className});
	}
	
	@Override
	<#if (table.pkColumn)??>
	public ${className}BO findByPrimaryKey(${table.pkColumn.javaType} ${table.pkColumn.columnNameLower}) throws Exception{
		BO${className} bo${className} = commonDAO.findById(BO${className}.class, ${table.pkColumn.columnNameLower});
	<#else>
	<#list table.columns as column>
    	<#if table.pkCount==0 && column_index==0>
	public ${className}BO findByPrimaryKey(${column.javaType} ${column.columnNameLower}) throws Exception {
		BO${className} bo${className} = commonDAO.findById(BO${className}.class, ${column.columnNameLower});
		</#if>
	</#list>
	</#if>
		return ${className}Mapper.INSTANCE.domainToBo(bo${className});
	}
	
	@Override
	public ${className}BO update(${className}BO ${classNameLower}BO) throws Exception {
		BO${className} bo${className} = ${className}Mapper.INSTANCE.boToDomain(${classNameLower}BO);
		bo${className} = commonDAO.saveOrUpdate(bo${className}, BO${className}.class);
		return ${className}Mapper.INSTANCE.domainToBo(bo${className});
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Page<${className}BO> findByConds(${className}QueryPageBO ${classNameLower}QueryPageBO) throws Exception {
		StringBuffer hql = new StringBuffer("SELECT o FROM BO${className} o WHERE 1 = 1");
		StringBuffer hqlCount = new StringBuffer("SELECT COUNT(o) FROM BO${className} o WHERE 1 = 1");
		StringBuffer hqlCondition = new StringBuffer();
		Map<String, Object> param = new HashMap<String, Object>();
		<#list table.columns as column>
		    <#if column.isNumberColumn>
	    if (${classNameLower}QueryPageBO.get${column.columnName}Begin() != null) {
			hqlCondition.append(" AND o.${column.columnNameLower} >= :${column.columnNameLower}Begin");
			param.put("${column.columnNameLower}Begin", ${classNameLower}QueryPageBO.get${column.columnName}Begin());
		}
	    if (${classNameLower}QueryPageBO.get${column.columnName}End() != null) {
			hqlCondition.append(" AND o.${column.columnNameLower} <= :${column.columnNameLower}End");
			param.put("${column.columnNameLower}End", ${classNameLower}QueryPageBO.get${column.columnName}End());
		}
	    	<#elseif column.isDateTimeColumn>
    	java.text.SimpleDateFormat sf${column.columnName} = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    if (${classNameLower}QueryPageBO.get${column.columnName}Begin() != null) {
			hqlCondition.append(" AND o.${column.columnNameLower} >= to_date('");
			hqlCondition.append(sf${column.columnName}.format(${classNameLower}QueryPageBO.get${column.columnName}Begin()));
			hqlCondition.append("', 'yyyy-MM-dd HH24:mi:ss')");
		}
	    if (${classNameLower}QueryPageBO.get${column.columnName}End() != null) {
			hqlCondition.append(" AND o.${column.columnNameLower} <= to_date('");
			hqlCondition.append(sf${column.columnName}.format(${classNameLower}QueryPageBO.get${column.columnName}End()));
			hqlCondition.append("', 'yyyy-MM-dd HH24:mi:ss')");
		}
		    <#elseif column.isStringColumn>
	    if (!StringUtils.isBlank(${classNameLower}QueryPageBO.get${column.columnName}())) {
			hqlCondition.append(" AND o.${column.columnNameLower} LIKE :${column.columnNameLower}");
			param.put("${column.columnNameLower}", "%"+${classNameLower}QueryPageBO.get${column.columnName}()+"%");
		}
	    	<#else>
	    if (${classNameLower}QueryPageBO.get${column.columnName}() != null) {
			hqlCondition.append(" AND o.${column.columnNameLower} = :${column.columnNameLower}");
			param.put("${column.columnNameLower}", ${classNameLower}QueryPageBO.get${column.columnName}());
		}
			</#if>
		</#list>
		hql.append(hqlCondition);
		hqlCount.append(hqlCondition);
		<#if (table.pkColumn)??>
		hql.append(" ORDER BY o.${table.pkColumn.columnNameLower} DESC");
		<#else>
		<#list table.columns as column>
	    	<#if table.pkCount==0 && column_index==0>
		hql.append(" ORDER BY o.${column.columnNameLower} DESC");
			</#if>
		</#list>
		</#if>
		List<BO${className}> bo${className}s = null;
		Page<${className}BO> page = new Page<${className}BO>();
		if ((${classNameLower}QueryPageBO.getPageNo() != null && ${classNameLower}QueryPageBO.getPageNo().compareTo(0) > 0) && (${classNameLower}QueryPageBO.getPageSize() != null && ${classNameLower}QueryPageBO.getPageSize().compareTo(0) > 0)) {
			Long count = (Long) commonDAO.findSingleResultByJPAQL(hqlCount.toString(), param);
			if (count == null || count.compareTo(0L) <= 0) {
				return page;
			}
			page.setRowCount(count.intValue());
			bo${className}s = commonDAO.findByJPAQL(hql.toString(), param, ${classNameLower}QueryPageBO.getPageNo(), ${classNameLower}QueryPageBO.getPageSize());
			page.generatePageCount(${classNameLower}QueryPageBO.getPageSize());
		} else {
			bo${className}s = commonDAO.findByJPAQL(hql.toString(), param);
		}
		page.setPageData(${className}Mapper.INSTANCE.domainToBo(bo${className}s));
		return page;
	}
	
}