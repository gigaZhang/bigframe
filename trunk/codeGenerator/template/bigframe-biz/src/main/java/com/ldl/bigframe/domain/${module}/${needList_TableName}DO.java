package com.ldl.bigframe.domain${module};

import java.util.Date;

import com.ldl.bigframe.domain.common.PageModel;

/**
 * ${table.name}
 * 
 * @author ${table.creator}
 * @version  ${table.tableName}DO.java ${.now}
 * 
 */
public class ${table.tableName}DO extends PageModel {

	private static final long serialVersionUID = 1L;
	
<#list table.columnList as column>
	private ${column.javaDataType} ${column.columnName}; //${column.comment}
</#list>	


<#list table.columnList as column>
	public ${column.javaDataType} get${column.columnName?cap_first}(){
		return ${column.columnName};
	}
	public void set${column.columnName?cap_first}(${column.javaDataType} ${column.columnName}) {
		this.${column.columnName} = ${column.columnName};
	}
</#list>	
}
