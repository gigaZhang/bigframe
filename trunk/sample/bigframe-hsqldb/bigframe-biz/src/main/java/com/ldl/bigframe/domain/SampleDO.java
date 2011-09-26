package com.ldl.bigframe.domain;

import java.util.Date;

import com.ldl.bigframe.domain.common.PageModel;

public class SampleDO extends PageModel {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String content;
	private Date createTime;
	private Date updateTime;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
