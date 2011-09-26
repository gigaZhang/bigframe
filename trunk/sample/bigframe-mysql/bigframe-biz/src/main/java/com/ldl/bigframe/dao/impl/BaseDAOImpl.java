package com.ldl.bigframe.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import com.ldl.bigframe.util.Assert;

import com.ldl.bigframe.dao.BaseDAO;
import com.ldl.bigframe.domain.common.PageModel;

public abstract class BaseDAOImpl<E> extends SqlSessionDaoSupport implements BaseDAO<E> {
	public static final String COUNT_POST = "_count";
	public static final String MAPPER_INSERT = "insert";
	public static final String MAPPER_INSERTBATCH = "insertBatch";
	public static final String MAPPER_DELETE = "delete";
	public static final String MAPPER_DELETEBATCH = "deleteBatch";
	public static final String MAPPER_UPDATE = "update";
	public static final String MAPPER_UPDATEBATCH = "updateBatch";
	public static final String MAPPER_GETOBJECT = "getObject";
	public static final String MAPPER_FINDLIST = "findList";
	public static final String MAPPER_FINDPAGEDLIST = "findPagedList";
	public static final String MAPPER_SELECTMAP = "selectMap";
	private Class<E> entityClass;
	
	public BaseDAOImpl(){
		//子类继承时获得DO的的真实类型
		Type type = getClass().getGenericSuperclass();
		Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
		this.entityClass = (Class<E>) trueType;
	}

	@Override
	public int delete() {
		return delete(generateStatement(MAPPER_DELETE));
	}

	@Override
	public int delete(Object parameter) {
		return delete(generateStatement(MAPPER_DELETE),parameter);
	}

	@Override
	public int delete(String statement) {
		return delete(statement,null);
	}

	@Override
	public int delete(String statement, Object parameter) {
		Assert.notEmpty(statement, "Property statement is required");
		return getSqlSession().delete(statement, parameter);
	}

	@Override
	public int deleteBatch(List<?> list) {
		return deleteBatch(generateStatement(MAPPER_DELETEBATCH),list);
	}

	@Override
	public int deleteBatch(String statement, List<?> list) {
		Assert.notEmpty(statement, "Property statement is required");
		if(list == null || list.isEmpty()){
			return 0;
		}
		SqlSession sqlSession = getSqlSession();
		for (int i = 0; i < list.size(); i++) {
			sqlSession.delete(statement, list.get(i));
		}
		return list.size();
	}

	@Override
	public List<E> findList() {
		return findList(generateStatement(MAPPER_FINDLIST));
	}

	@Override
	public List<E> findList(Object parameter) {
		return findList(generateStatement(MAPPER_FINDLIST),parameter);
	}

	@Override
	public List<E> findList(String statement) {
		return findList(statement,null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> findList(String statement, Object parameter) {
		Assert.notEmpty(statement, "Property statement is required");
		return (List<E>)getSqlSession().selectList(statement, parameter);
	}

	@Override
	public E getObject() {
		return getObject(generateStatement(MAPPER_GETOBJECT));
	}

	@Override
	public E getObject(Object parameter) {
		return getObject(generateStatement(MAPPER_GETOBJECT),parameter);
	}	

	@Override
	public E getObject(String statement) {
		return getObject(statement,null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public E getObject(String statement, Object parameter) {
		Assert.notEmpty(statement, "Property statement is required");
		return (E)getSqlSession().selectOne(statement, parameter);
	}

	@Override
	public int insert() {
		return insert(generateStatement(MAPPER_INSERT));
	}

	@Override
	public int insert(Object parameter) {
		return insert(generateStatement(MAPPER_INSERT),parameter);
	}

	@Override
	public int insert(String statement) {
		return insert(statement,null);
	}

	@Override
	public int insert(String statement, Object parameter) {
		Assert.notEmpty(statement, "Property statement is required");
		return getSqlSession().insert(statement, parameter);
	}

	@Override
	public int insertBatch(List<?> list) {
		return insertBatch(generateStatement(MAPPER_INSERTBATCH),list);
	}

	@Override
	public int insertBatch(String statement, List<?> list) {
		Assert.notEmpty(statement, "Property statement is required");
		if(list == null || list.isEmpty()){
			return 0;
		}
		SqlSession sqlSession = getSqlSession();
		for (int i = 0; i < list.size(); i++) {
			sqlSession.insert(statement, list.get(i));
		}
		return list.size();
	}

	@Override
	public Map<?, E> selectMap(String mapKey) {
		return selectMap(generateStatement(MAPPER_SELECTMAP),mapKey);
	}

	@Override
	public Map<?, E> selectMap(Object parameter, String mapKey) {
		return selectMap(generateStatement(MAPPER_SELECTMAP),parameter,mapKey);
	}

	@Override
	public Map<?, E> selectMap(String statement, String mapKey) {
		return selectMap(statement,null,mapKey);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<?, E> selectMap(String statement, Object parameter, String mapKey) {
		Assert.notEmpty(statement, "Property statement is required");
		Assert.notEmpty(statement, "Property mapKey is required");
		return getSqlSession().selectMap(statement, parameter, mapKey);
	}

	@Override
	public int update() {
		return update(generateStatement(MAPPER_UPDATE));
	}

	@Override
	public int update(Object parameter) {
		return update(generateStatement(MAPPER_UPDATE),parameter);
	}

	@Override
	public int update(String statement) {
		return update(statement,null);
	}

	@Override
	public int update(String statement, Object parameter) {
		Assert.notEmpty(statement, "Property statement is required");
		return getSqlSession().update(statement, parameter);
	}

	@Override
	public int updateBatch(List<?> list) {
		return updateBatch(generateStatement(MAPPER_UPDATEBATCH),list);
	}

	@Override
	public int updateBatch(String statement, List<?> list) {
		Assert.notEmpty(statement, "Property statement is required");
		if(list == null || list.isEmpty()){
			return 0;
		}
		SqlSession sqlSession = getSqlSession();
		for (int i = 0; i < list.size(); i++) {
			sqlSession.update(statement, list.get(i));
		}
		return list.size();
	}

	@Override
	public PageModel findPagedList(PageModel parameter) {
		String statement = generateStatement(MAPPER_FINDPAGEDLIST);
		return findPagedList(statement,statement + COUNT_POST ,parameter);
	}

	@Override
	public PageModel findPagedList(String statement, PageModel parameter) {
		return findPagedList(statement,statement + COUNT_POST,parameter);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PageModel findPagedList(String statement,String countStatement,PageModel parameter) {
		Assert.notEmpty(statement, "Property statement is required");
		Assert.notEmpty(statement, "Property countStatement is required");
		
		List<E> list = new ArrayList<E>();
		if(parameter == null){
			return new PageModel();
		}
		//首次查询时返回总记录数，以后查询时传入totalItem，则不再查询。
		if(parameter.getTotalItem() <= 0){
			// 得到数据记录总数
			Integer totalItem = (Integer) getSqlSession().selectOne(countStatement, parameter);
			if(totalItem != null){
				parameter.setTotalItem(totalItem.intValue());
			}else if(totalItem == null || totalItem == 0){
				return new PageModel();
			}
		}
		list = (List<E>)getSqlSession().selectList(statement, parameter);
		parameter.setList(list);
		return parameter;
	}
	
	/**
	 * 生成statement
	 * @param mapperId:sqlmap配置文件sql语句id
	 * @return
	 */
	public String generateStatement(String mapperId){
		return entityClass.getName() + "." + mapperId;
	}

}
