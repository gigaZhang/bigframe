package com.ldl.bigframe.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ldl.bigframe.domain.SampleDO;
import com.ldl.bigframe.service.sample.SampleBO;
import com.opensymphony.xwork2.Action;

public class SampleAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private Map json;
	private SampleBO sampleBO;
	private List<SampleDO> sampleList;
	private Integer id;
	private SampleDO sampleDO;
	
	public SampleDO getSampleDO() {
		return sampleDO;
	}
	public void setSampleDO(SampleDO sampleDO) {
		this.sampleDO = sampleDO;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<SampleDO> getSampleList() {
		return sampleList;
	}
	public void setSampleList(List<SampleDO> sampleList) {
		this.sampleList = sampleList;
	}
	public Map getJson() {
		return json;
	}
	public void setJson(Map json) {
		this.json = json;
	}
	
	public SampleBO getSampleBO() {
		return sampleBO;
	}

	public void setSampleBO(SampleBO sampleBO) {
		this.sampleBO = sampleBO;
	}

	public String list(){
		if(sampleDO == null){
			sampleDO = new SampleDO();
		}
		sampleDO.setToPage(pageModel.getToPage());
		pageModel = sampleBO.findPagedList(sampleDO);
		return Action.SUCCESS;
	}
	public String delete(){
		sampleBO.deleteSample(id);
		return Action.SUCCESS;
	}
	public String add(){
		return Action.SUCCESS;
	}
	public String doAdd(){
		if(sampleDO != null){
			sampleBO.addSample(sampleDO);
		}
		return Action.SUCCESS;
	}
	
	public String doUpdate(){
		if(sampleDO != null){
			sampleBO.updateSample(sampleDO);
		}
		return Action.SUCCESS;
	}
	public String update(){
		SampleDO sampleDO = sampleBO.getSampleById(id);
		if(sampleDO == null){
			sampleDO = new SampleDO();
		}
		json = new HashMap();
		json.put("s", sampleDO);
		return Action.SUCCESS;
	}	
	
}
