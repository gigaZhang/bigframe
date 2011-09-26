package com.ldl.bigframe.domain.common;

import java.util.ArrayList;
import java.util.List;



/**
 * 分页数据模型基类。
 * @author nandi.ldl
 */
public class PageModel extends BaseDO{
	private static final long serialVersionUID = 1L;
	
	public static int PAGE_SIZE = 10;//默认页面大小为10
	public static int FIRST_PAGE = 1;//第一页
	public static int MAX_PAGE_COUNT = 15;
	
	/**
	 * 由于mysql和oracle的startRow不一样，所以这里做个兼容
	 */
	public static String DB_TYPE_MYSQL = "mysql"; 
	public static String DB_TYPE_ORACLE = "oracle"; 
	
	private int toPage = FIRST_PAGE;// 当前页码
	
	private int pageSize = PAGE_SIZE;// 每页显示记录数
	
	private int maxPageCount = MAX_PAGE_COUNT;//页面显示使用google分页样式时的页码列表最大数量，比如[1][2][3][4]....[15],[3][2][3][4]....[18]
	
	private int totalItem;// 查询出的数据总数
	private List<Integer> pageNumList;//页码列表[1][2][3][4]...
	
	private String DBType = DB_TYPE_MYSQL;//数据库类型
	
	private List list = new ArrayList(); //查询返回的数据列表
	
	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getDBType() {
		return DBType;
	}

	public void setDBType(String dBType) {
		DBType = dBType;
	}
	public int getMaxPageCount() {
		return maxPageCount < 1? MAX_PAGE_COUNT:maxPageCount;
	}

	public void setMaxPageCount(int maxPageCount) {
		this.maxPageCount = maxPageCount;
	}

	public int getToPage() {
		return toPage;
	}
	
	public void setToPage(int toPage) {
		this.toPage = toPage;
	}
	
	public int getTotalItem() {
		return totalItem;
	}
	
	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}
	
	public int getPageSize() {
		return pageSize <= 0?PAGE_SIZE:pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}	
	
	public List<Integer> getPageNumList() {
		pageNumList = new ArrayList<Integer>();
		int totalPage = this.getTotalPage();
		toPage = toPage< 1?FIRST_PAGE:(toPage >totalPage?totalPage:toPage);
		int currentOption = toPage;
		int endOption = currentOption + getMaxPageCount()/2;
		if(endOption > totalPage){
			endOption =  totalPage;
		}
		int beginOption = endOption - getMaxPageCount() + 1;
		if(beginOption <= 0){
			beginOption = 1;
		}
		for(int i=beginOption;i<=endOption;i++){
			pageNumList.add(i);
		}		
		return pageNumList;
	}	
	
	/**
	 * 判断当前页是否为第一页
	 * 
	 * @return boolean
	 */
	public boolean getIsFirstPage() {
		return toPage == FIRST_PAGE;
	}
	
	/**
	 * 判断当前页是否为最后一页
	 * 
	 * @return boolean
	 */
	public boolean getIsLastPage() {
		int totalPage = this.getTotalPage();
		return totalPage == 0 || toPage == totalPage;
	}

	/**
	 * 得到上一页的页数
	 * 
	 * @return int 如果当前页没有上一页，返回1
	 */
	public int getPrevPage() {
		int back = toPage - 1;
		return back < 1?FIRST_PAGE:back;
	}

	/**
	 * 得到下一页的页数
	 * 
	 * @return int 如果当前也没有下一页，返回总页数
	 */
	public int getNextPage() {
		int next = toPage + 1;
		int totalPage = this.getTotalPage();
		if (next >= totalPage) {
			next = totalPage;
		}
		return next;
	}

	/**
	 * 得到第一页的页数
	 * 
	 * @return boolean
	 */
	public int getFirstPage() {
		return FIRST_PAGE;
	}
	
	/**
	 * 得到最后一页的页数
	 * 
	 * @return boolean
	 */
	public int getLastPage() {
		int totalPage = this.getTotalPage();
		return totalPage <= 0?FIRST_PAGE:totalPage;
	}	
	
	/**
	 * 得到要显示的总页数
	 * 
	 * @return int
	 */
	public int getTotalPage() {
		int pageSize = getPageSize();
		return totalItem <= 0?0:(totalItem + pageSize -1) / pageSize;
	}
	
	/**
	 * 得到要检索的起始行数 
	 */
	public Integer getStartRow() {
		int totalPage = this.getTotalPage();
		toPage = toPage< 1?FIRST_PAGE:(toPage >totalPage?totalPage:toPage);
		Integer row = new Integer((toPage - 1) * getPageSize());
		if(DB_TYPE_ORACLE.equals(DBType)){
			row = row + 1;
		}
		return row;
	}
	
	public Integer getEndRow() {
		int totalPage = this.getTotalPage();
		toPage = toPage< 1?FIRST_PAGE:(toPage >totalPage?totalPage:toPage);		
		return new Integer(toPage * getPageSize());
	}
}
