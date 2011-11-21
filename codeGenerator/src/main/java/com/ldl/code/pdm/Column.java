package com.ldl.code.pdm;

import com.ldl.code.util.StringUtils;

public class Column {
	
	private String name;
	private String code;//列名
	private String comment;
	private String dataType;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getComment() {
		if(comment == null || "".equals(comment)){
			return name;
		}		
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	/*
	 * 根据jdbc类型返回java对应的类型
	 */
	public String getJavaDataType(){
		if(dataType == null || "".equals(dataType)){
			return DataType.STRING;
		}
		String type = dataType;
		if(dataType.indexOf("(") != -1){
			type = dataType.substring(0,dataType.indexOf("(") + 1);
		}
		return JdbcDataType.forType(type);
	}
	
	/**
	 * 根据code返回java对应的属性名
	 * @return
	 */
	public String getColumnName(){
		String columnName = "";
		if(code == null || "".equals(code)){
			return columnName;
		}
		String[] codes = code.split("_");
		for (int i = 0; i < codes.length; i++) {
			String code_ = codes[i].toLowerCase();
			if(i >= 1){
				code_ = StringUtils.capitalize(codes[i].toLowerCase());
			}
			columnName += code_;
		}
		return columnName;		
	}
}
