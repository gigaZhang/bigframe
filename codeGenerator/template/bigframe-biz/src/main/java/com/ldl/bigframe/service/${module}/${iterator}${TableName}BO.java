package com.ldl.bigframe.service${module};

import java.util.List;
import java.util.Map;

import com.ldl.bigframe.domain${module}.${table.tableName}DO;
import com.ldl.bigframe.domain.common.PageModel;
/**
 * ${table.name}
 * 
 * @author ${table.creator}
 * @version  ${table.tableName}BO.java ${.now}
 * 
 */
public interface ${table.tableName}BO {
	public void add${table.tableName}(${table.tableName}DO ${table.tableName?uncap_first}DO);
	public void addBatch${table.tableName}(List<${table.tableName}DO> ${table.tableName?uncap_first}List);
	public int delete${table.tableName}(Integer id);
	public void deleteBatch${table.tableName}(List<${table.tableName}DO> ${table.tableName?uncap_first}List);
	public void update${table.tableName}(${table.tableName}DO ${table.tableName?uncap_first}DO);
	public void updateBatch${table.tableName}(List<${table.tableName}DO> ${table.tableName?uncap_first}List);
	public ${table.tableName}DO get${table.tableName}ById(Integer id);
	public List<${table.tableName}DO> find${table.tableName}List(${table.tableName}DO ${table.tableName?uncap_first}DO);
	public Map<Integer,${table.tableName}DO> get${table.tableName}Map(${table.tableName}DO ${table.tableName?uncap_first}DO);
	public PageModel findPagedList(${table.tableName}DO ${table.tableName?uncap_first}DO);
}
