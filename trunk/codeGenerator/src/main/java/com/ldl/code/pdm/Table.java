package com.ldl.code.pdm;

import java.text.SimpleDateFormat;
import java.util.List;

import com.ldl.code.util.StringUtils;

public class Table {
	
	private String name;
	private String code;//±íÃû
	private String comment;
	private String creator;
	private Long creationDate;
	private Column primaryColumn;//Ö÷¼ü×Ö¶Î
	private List<Column> columnList;
	
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
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreationDate() {
		SimpleDateFormat dateformat= new SimpleDateFormat("yyyy-MM-dd");
		return dateformat.format(creationDate);
	}
	public void setCreationDate(Long creationDate) {
		this.creationDate = creationDate;
	}

	public Column getPrimaryColumn() {
		return primaryColumn;
	}
	public void setPrimaryColumn(Column primaryColumn) {
		this.primaryColumn = primaryColumn;
	}
	public List<Column> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<Column> columnList) {
		this.columnList = columnList;
	}
	
	public String getTableName(){
		String tableName = "";
		if(code == null || "".equals(code)){
			return tableName;
		}
		String[] codes = code.split("_");
		for (int i = 0; i < codes.length; i++) {
			tableName += StringUtils.capitalize(codes[i].toLowerCase());
		}
		return tableName;
	}
	
}
