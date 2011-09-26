package com.ldl.bigframe.service.sample;

import java.util.List;
import java.util.Map;

import com.ldl.bigframe.domain.SampleDO;
import com.ldl.bigframe.domain.common.PageModel;

public interface SampleBO {
	public void addSample(SampleDO sampleDO);
	public void addBatchSample(List<SampleDO> sampleList);
	public int deleteSample(Integer id);
	public void deleteBatchSample(List<SampleDO> sampleList);
	public void updateSample(SampleDO sampleDO);
	public void updateBatchSample(List<SampleDO> sampleList);
	public SampleDO getSampleById(Integer id);
	public List<SampleDO> findSampleList(SampleDO sampleDO);
	public Map<Integer,SampleDO> getSampleMap(SampleDO sampleDO);
	public PageModel findPagedList(SampleDO sampleDO);
}
