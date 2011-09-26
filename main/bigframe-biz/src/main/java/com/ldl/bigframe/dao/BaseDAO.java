package com.ldl.bigframe.dao;

import java.util.List;
import java.util.Map;

import com.ldl.bigframe.domain.common.PageModel;

/**
 * DAO基类
 * @author nandi.ldl
 * @param <E>子类的DO
 * 参考sample的使用方法
 * statement :缺省使用namespace + DAO方法名
 */
public interface BaseDAO<E> {
	/*
	 * 插入数据
	 */
	public int insert();
	public int insert(Object parameter);
	public int insert(String statement);
	public int insert(String statement,Object parameter);
	public int insertBatch(List<?> list);
	public int insertBatch(String statement,List<?> list);
	
	/*
	 * 删除数据
	 */
	public int delete();
	public int delete(Object parameter);
	public int delete(String statement);
	public int delete(String statement,Object parameter);
	public int deleteBatch(List<?> list);
	public int deleteBatch(String statement,List<?> list);	
	
	/*
	 * 更新数据
	 */
	public int update();
	public int update(Object parameter);
	public int update(String statement);
	public int update(String statement,Object parameter);
	public int updateBatch(List<?> list);
	public int updateBatch(String statement,List<?> list);	

	/*
	 * 获得单个对象
	 */
	public E getObject();
	public E getObject(Object parameter);
	public E getObject(String statement);
	public E getObject(String statement,Object parameter);
	
	/*
	 * 获得list
	 */
	public List<E> findList();
	public List<E> findList(Object parameter);
	public List<E> findList(String statement);
	public List<E> findList(String statement,Object parameter);
	
	/*
	 * 分页查询
	 */
	public PageModel findPagedList(PageModel parameter);
	public PageModel findPagedList(String statement,PageModel parameter);
	/**
	 * 
	 * @param statement:查询数据的statement名字
	 * @param countStatement:查询总记录数的statement名字
	 * @param parameter
	 * @return
	 */
	public PageModel findPagedList(String statement,String countStatement,PageModel parameter);
	
	/*
	 * 查询返回map
	 */
	public Map<?,E> selectMap(String mapKey);
	public Map<?,E> selectMap(Object parameter,String mapKey);
	public Map<?,E> selectMap(String statement,String mapKey);
	public Map<?,E> selectMap(String statement,Object parameter,String mapKey);

}
