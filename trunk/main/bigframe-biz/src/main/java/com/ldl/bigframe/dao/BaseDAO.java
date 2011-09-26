package com.ldl.bigframe.dao;

import java.util.List;
import java.util.Map;

import com.ldl.bigframe.domain.common.PageModel;

/**
 * DAO����
 * @author nandi.ldl
 * @param <E>�����DO
 * �ο�sample��ʹ�÷���
 * statement :ȱʡʹ��namespace + DAO������
 */
public interface BaseDAO<E> {
	/*
	 * ��������
	 */
	public int insert();
	public int insert(Object parameter);
	public int insert(String statement);
	public int insert(String statement,Object parameter);
	public int insertBatch(List<?> list);
	public int insertBatch(String statement,List<?> list);
	
	/*
	 * ɾ������
	 */
	public int delete();
	public int delete(Object parameter);
	public int delete(String statement);
	public int delete(String statement,Object parameter);
	public int deleteBatch(List<?> list);
	public int deleteBatch(String statement,List<?> list);	
	
	/*
	 * ��������
	 */
	public int update();
	public int update(Object parameter);
	public int update(String statement);
	public int update(String statement,Object parameter);
	public int updateBatch(List<?> list);
	public int updateBatch(String statement,List<?> list);	

	/*
	 * ��õ�������
	 */
	public E getObject();
	public E getObject(Object parameter);
	public E getObject(String statement);
	public E getObject(String statement,Object parameter);
	
	/*
	 * ���list
	 */
	public List<E> findList();
	public List<E> findList(Object parameter);
	public List<E> findList(String statement);
	public List<E> findList(String statement,Object parameter);
	
	/*
	 * ��ҳ��ѯ
	 */
	public PageModel findPagedList(PageModel parameter);
	public PageModel findPagedList(String statement,PageModel parameter);
	/**
	 * 
	 * @param statement:��ѯ���ݵ�statement����
	 * @param countStatement:��ѯ�ܼ�¼����statement����
	 * @param parameter
	 * @return
	 */
	public PageModel findPagedList(String statement,String countStatement,PageModel parameter);
	
	/*
	 * ��ѯ����map
	 */
	public Map<?,E> selectMap(String mapKey);
	public Map<?,E> selectMap(Object parameter,String mapKey);
	public Map<?,E> selectMap(String statement,String mapKey);
	public Map<?,E> selectMap(String statement,Object parameter,String mapKey);

}
