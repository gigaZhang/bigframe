package com.ldl.bigframe.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * �����Ŀ������gbkʱ��ajax�������⡣
 * ajax�ύ�����ַ��ı�����utf-8,struts2�ı�����gbk,����ʱ�����롣
 * ���filter��������ԭ������struts2������������ַ���֮ǰ���Ȳ���utf-8����ķ�ʽ����һ�Σ���ԭ�ַ�����
 * @author nandi.ldl
 *
 */
public class CharacterEncodingFilter implements Filter {
	  
	  
	  public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {  
		  HttpServletRequest request = (HttpServletRequest) servletRequest;  
		  HttpServletResponse response = (HttpServletResponse) servletResponse;  
		  
		  //����httpͷ�ļ����ж�����������Ƿ���ajax
		  String requestType = request.getHeader("x-requested-with"); 
		  if (requestType != null && "XMLHttpRequest".equals(requestType)){
			  request.getParameterMap();//����servlet�����Ľ����ַ���
		  }
		  filterChain.doFilter(request, response); 
	  }  
	  
	  public void destroy() {  
	  }  
	  public void init(FilterConfig arg0) throws ServletException {  
	  // TODO Auto-generated method stub  
	   
	  }  
	  

}
