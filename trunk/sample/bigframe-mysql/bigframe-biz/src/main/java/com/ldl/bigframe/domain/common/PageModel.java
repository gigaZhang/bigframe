package com.ldl.bigframe.domain.common;

import java.util.ArrayList;
import java.util.List;



/**
 * ��ҳ����ģ�ͻ��ࡣ
 * @author nandi.ldl
 */
public class PageModel extends BaseDO{
	private static final long serialVersionUID = 1L;
	
	public static int PAGE_SIZE = 10;//Ĭ��ҳ���СΪ10
	public static int FIRST_PAGE = 1;//��һҳ
	public static int MAX_PAGE_COUNT = 15;
	
	/**
	 * ����mysql��oracle��startRow��һ��������������������
	 */
	public static String DB_TYPE_MYSQL = "mysql"; 
	public static String DB_TYPE_ORACLE = "oracle"; 
	
	private int toPage = FIRST_PAGE;// ��ǰҳ��
	
	private int pageSize = PAGE_SIZE;// ÿҳ��ʾ��¼��
	
	private int maxPageCount = MAX_PAGE_COUNT;//ҳ����ʾʹ��google��ҳ��ʽʱ��ҳ���б��������������[1][2][3][4]....[15],[3][2][3][4]....[18]
	
	private int totalItem;// ��ѯ������������
	private List<Integer> pageNumList;//ҳ���б�[1][2][3][4]...
	
	private String DBType = DB_TYPE_MYSQL;//���ݿ�����
	
	private List list = new ArrayList(); //��ѯ���ص������б�
	
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
	 * �жϵ�ǰҳ�Ƿ�Ϊ��һҳ
	 * 
	 * @return boolean
	 */
	public boolean getIsFirstPage() {
		return toPage == FIRST_PAGE;
	}
	
	/**
	 * �жϵ�ǰҳ�Ƿ�Ϊ���һҳ
	 * 
	 * @return boolean
	 */
	public boolean getIsLastPage() {
		int totalPage = this.getTotalPage();
		return totalPage == 0 || toPage == totalPage;
	}

	/**
	 * �õ���һҳ��ҳ��
	 * 
	 * @return int �����ǰҳû����һҳ������1
	 */
	public int getPrevPage() {
		int back = toPage - 1;
		return back < 1?FIRST_PAGE:back;
	}

	/**
	 * �õ���һҳ��ҳ��
	 * 
	 * @return int �����ǰҲû����һҳ��������ҳ��
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
	 * �õ���һҳ��ҳ��
	 * 
	 * @return boolean
	 */
	public int getFirstPage() {
		return FIRST_PAGE;
	}
	
	/**
	 * �õ����һҳ��ҳ��
	 * 
	 * @return boolean
	 */
	public int getLastPage() {
		int totalPage = this.getTotalPage();
		return totalPage <= 0?FIRST_PAGE:totalPage;
	}	
	
	/**
	 * �õ�Ҫ��ʾ����ҳ��
	 * 
	 * @return int
	 */
	public int getTotalPage() {
		int pageSize = getPageSize();
		return totalItem <= 0?0:(totalItem + pageSize -1) / pageSize;
	}
	
	/**
	 * �õ�Ҫ��������ʼ���� 
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
