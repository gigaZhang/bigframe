package com.ldl.bigframe.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.ldl.bigframe.domain.SampleDO;
import com.ldl.bigframe.domain.common.PageModel;
import com.ldl.bigframe.service.sample.SampleBO;
public class SampleBOTest extends BaseTestCase {
	private SampleBO sampleBO;

	@Autowired
	public void setSampleBO(SampleBO sampleBO) {
		this.sampleBO = sampleBO;
	}
	
	@Rollback(false)
	@Test
	public void test01_insertSample(){
		for (int i = 0; i < 10; i++) {
			SampleDO sampleDO = new SampleDO();
			sampleDO.setName("name" + i);
			sampleDO.setContent("content" + i);
			sampleBO.addSample(sampleDO);		
		}
	}
	
	@Rollback(false)
	@Test
	public void test02_insertBatchSample(){
		List<SampleDO> list = new ArrayList<SampleDO>();
		for (int i = 0; i < 10; i++) {
			SampleDO sampleDO = new SampleDO();
			sampleDO.setName("batchName" + i);
			sampleDO.setContent("batchContent" + i);
			list.add(sampleDO);
		}
		sampleBO.addBatchSample(list);
	}
	
	@Rollback(false)
	@Test
	public void test03_deleteSample(){
		int count = sampleBO.deleteSample(9);
		Assert.assertEquals("删除成功的数量：",1,count);
	}
	
	@Rollback(false)
	@Test
	public void test04_deleteBatchSample(){
		List<SampleDO> list = new ArrayList<SampleDO>();
		for (int i = 10; i < 12; i++) {
			SampleDO sampleDO = new SampleDO();
			sampleDO.setId(i);
			list.add(sampleDO);
		}
		sampleBO.deleteBatchSample(list);
	}
	
	@Rollback(false)
	@Test
	public void test05_updateSample(){
		SampleDO sampleDO = new SampleDO();
		sampleDO.setId(20);
		sampleDO.setName("updateTest");
		sampleBO.updateSample(sampleDO);
	}
	
	@Rollback(false)
	@Test
	public void test06_updateBatchSample(){
		List<SampleDO> list = new ArrayList<SampleDO>();
		for (int i = 18; i < 20; i++) {
			SampleDO sampleDO = new SampleDO();
			sampleDO.setId(i);
			sampleDO.setName("updateTest" + i);
			list.add(sampleDO);
		}
		sampleBO.updateBatchSample(list);
	}	
	
	@Test
	public void test07_getSampleById(){
		String name = sampleBO.getSampleById(20).getName();
		Assert.assertEquals("sample21", name);
	}
	
	@Test
	public void test08_findSampleList(){
		List<SampleDO> list = sampleBO.findSampleList(null);
		Assert.assertEquals(100, list.size());
	}
	
	@Test
	public void test09_getSampleMap(){
		Map<Integer, SampleDO> map = sampleBO.getSampleMap(null);
		Assert.assertEquals("sample1",map.get(1).getName());
	}
	
	@Test
	public void test10_findPagedSampleList(){
		SampleDO sampleDO = new SampleDO();
		sampleDO.setToPage(2);
		PageModel pm = sampleBO.findPagedList(sampleDO);
		List<SampleDO> list = pm.getList();
		for (SampleDO sd : list) {
			System.out.println(sd.getId() + "_" + sd.getName());
		}
	}
}
