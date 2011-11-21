package com.ldl.bigframe.service${module}.impl;



import java.util.List;
import java.util.Map;

import com.ldl.bigframe.dao${module}.${table.tableName}DAO;
import com.ldl.bigframe.domain${module}.${table.tableName}DO;
import com.ldl.bigframe.domain.common.PageModel;
import com.ldl.bigframe.service.BaseBOImpl;
import com.ldl.bigframe.service${module}.${table.tableName}BO;
/**
 * ${table.name}
 * 
 * @author ${table.creator}
 * @version  ${table.tableName}BOImpl.java ${.now}
 * 
 */
public class ${table.tableName}BOImpl extends BaseBOImpl implements ${table.tableName}BO {
	private ${table.tableName}DAO ${table.tableName?uncap_first}DAO;

	public ${table.tableName}DAO get${table.tableName}DAO() {
		return ${table.tableName?uncap_first}DAO;
	}
	public void set${table.tableName}DAO(${table.tableName}DAO ${table.tableName?uncap_first}DAO) {
		this.${table.tableName?uncap_first}DAO = ${table.tableName?uncap_first}DAO;
	}
	
	@Override
	public void add${table.tableName}(${table.tableName}DO ${table.tableName?uncap_first}DO) {
		${table.tableName?uncap_first}DAO.insert(${table.tableName?uncap_first}DO);
	}
	@Override
	public int delete${table.tableName}(Integer id) {
		return ${table.tableName?uncap_first}DAO.delete(id);
	}
	@Override
	public ${table.tableName}DO get${table.tableName}ById(Integer id){
		return ${table.tableName?uncap_first}DAO.getObject(id);
	}
	@Override
	public List<${table.tableName}DO> find${table.tableName}List(${table.tableName}DO ${table.tableName?uncap_first}DO) {
		return ${table.tableName?uncap_first}DAO.findList(${table.tableName?uncap_first}DO);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, ${table.tableName}DO> get${table.tableName}Map(${table.tableName}DO ${table.tableName?uncap_first}DO) {
		return (Map<Integer, ${table.tableName}DO>)${table.tableName?uncap_first}DAO.selectMap(${table.tableName}DO.class.getName()+ ".findList"  ,${table.tableName?uncap_first}DO, "id");
	}
	@Override
	public void update${table.tableName}(${table.tableName}DO ${table.tableName?uncap_first}DO) {
		${table.tableName?uncap_first}DAO.update(${table.tableName?uncap_first}DO);
	}
	@Override
	public void addBatch${table.tableName}(List<${table.tableName}DO> ${table.tableName?uncap_first}List) {
		${table.tableName?uncap_first}DAO.insertBatch(${table.tableName}DO.class.getName() + ".insert",${table.tableName?uncap_first}List);
	}
	@Override
	public void deleteBatch${table.tableName}(List<${table.tableName}DO> ${table.tableName?uncap_first}List) {
		${table.tableName?uncap_first}DAO.deleteBatch(${table.tableName}DO.class.getName() + ".delete",${table.tableName?uncap_first}List);
	}
	@Override
	public void updateBatch${table.tableName}(List<${table.tableName}DO> ${table.tableName?uncap_first}List) {
		${table.tableName?uncap_first}DAO.updateBatch(${table.tableName}DO.class.getName() + ".update",${table.tableName?uncap_first}List);
	}
	
	@Override
	public PageModel findPagedList(${table.tableName}DO ${table.tableName?uncap_first}DO) {
		return ${table.tableName?uncap_first}DAO.findPagedList(${table.tableName?uncap_first}DO);
	}
}
