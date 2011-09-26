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
 * 解决项目编码是gbk时，ajax乱码问题。
 * ajax提交请求字符的编码是utf-8,struts2的编码是gbk,解析时会乱码。
 * 这个filter解决乱码的原理是在struts2解析编码解析字符串之前，先采用utf-8编码的方式解析一次，还原字符串。
 * @author nandi.ldl
 *
 */
public class CharacterEncodingFilter implements Filter {
	  
	  
	  public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {  
		  HttpServletRequest request = (HttpServletRequest) servletRequest;  
		  HttpServletResponse response = (HttpServletResponse) servletResponse;  
		  
		  //根据http头文件，判断请求的类型是否是ajax
		  String requestType = request.getHeader("x-requested-with"); 
		  if (requestType != null && "XMLHttpRequest".equals(requestType)){
			  request.getParameterMap();//触发servlet容器的解析字符串
		  }
		  filterChain.doFilter(request, response); 
	  }  
	  
	  public void destroy() {  
	  }  
	  public void init(FilterConfig arg0) throws ServletException {  
	  // TODO Auto-generated method stub  
	   
	  }  
	  

}
