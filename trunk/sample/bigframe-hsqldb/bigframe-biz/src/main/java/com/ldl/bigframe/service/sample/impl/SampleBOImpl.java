package com.ldl.bigframe.service.sample.impl;



import java.util.List;
import java.util.Map;

import com.ldl.bigframe.dao.SampleDAO;
import com.ldl.bigframe.domain.SampleDO;
import com.ldl.bigframe.domain.common.PageModel;
import com.ldl.bigframe.service.BaseBOImpl;
import com.ldl.bigframe.service.sample.SampleBO;

public class SampleBOImpl extends BaseBOImpl implements SampleBO {
	private SampleDAO sampleDAO;

	public SampleDAO getSampleDAO() {
		return sampleDAO;
	}
	public void setSampleDAO(SampleDAO sampleDAO) {
		this.sampleDAO = sampleDAO;
	}
	
	@Override
	public void addSample(SampleDO sampleDO) {
		sampleDAO.insert(sampleDO);
	}
	@Override
	public int deleteSample(Integer id) {
		return sampleDAO.delete(id);
	}
	@Override
	public SampleDO getSampleById(Integer id){
		return sampleDAO.getObject(id);
	}
	@Override
	public List<SampleDO> findSampleList(SampleDO sampleDO) {
		return sampleDAO.findList(sampleDO);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, SampleDO> getSampleMap(SampleDO sampleDO) {
		return (Map<Integer, SampleDO>)sampleDAO.selectMap(SampleDO.class.getName()+ ".findList"  ,sampleDO, "id");
	}
	@Override
	public void updateSample(SampleDO sampleDO) {
		sampleDAO.update(sampleDO);
	}
	@Override
	public void addBatchSample(List<SampleDO> sampleList) {
		sampleDAO.insertBatch(SampleDO.class.getName() + ".insert",sampleList);
		
	}
	@Override
	public void deleteBatchSample(List<SampleDO> sampleList) {
		sampleDAO.deleteBatch(SampleDO.class.getName() + ".delete",sampleList);
	}
	@Override
	public void updateBatchSample(List<SampleDO> sampleList) {
		sampleDAO.updateBatch(SampleDO.class.getName() + ".update",sampleList);
	}
	
	@Override
	public PageModel findPagedList(SampleDO sampleDO) {
		return sampleDAO.findPagedList(sampleDO);
	}
}
