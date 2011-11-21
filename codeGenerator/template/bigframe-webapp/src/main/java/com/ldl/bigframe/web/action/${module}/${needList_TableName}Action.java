package com.ldl.bigframe.web.action${module};

import com.ldl.bigframe.domain${module}.${table.tableName}DO;
import com.ldl.bigframe.service${module}.${table.tableName}BO;
import com.ldl.bigframe.web.action.BaseAction;
import com.opensymphony.xwork2.Action;
/**
 * ${table.name}
 * 
 * @author ${table.creator}
 * @version  ${table.tableName}Action.java ${.now}
 * 
 */
public class ${table.tableName}Action extends BaseAction {
	private ${table.tableName}BO ${table.tableName?uncap_first}BO;
	private ${table.tableName}DO ${table.tableName?uncap_first}DO;
	
	public ${table.tableName}DO get${table.tableName}DO() {
		return ${table.tableName?uncap_first}DO;
	}
	public void set${table.tableName}DO(${table.tableName}DO ${table.tableName?uncap_first}DO) {
		this.${table.tableName?uncap_first}DO = ${table.tableName?uncap_first}DO;
	}
	
	public ${table.tableName}BO get${table.tableName}BO() {
		return ${table.tableName?uncap_first}BO;
	}

	public void set${table.tableName}BO(${table.tableName}BO ${table.tableName?uncap_first}BO) {
		this.${table.tableName?uncap_first}BO = ${table.tableName?uncap_first}BO;
	}

	public String list(){
		return Action.SUCCESS;
	}
}
