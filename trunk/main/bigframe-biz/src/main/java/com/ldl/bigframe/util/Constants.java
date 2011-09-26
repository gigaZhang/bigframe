package com.ldl.bigframe.util;

public class Constants {
	
	
	public static String STATIC_FILE_URL_PREFIX = "";////静态文件URL前缀

	public String getSTATIC_FILE_URL_PREFIX() {
		return STATIC_FILE_URL_PREFIX;
	}

	public void setSTATIC_FILE_URL_PREFIX(String static_file_url_prefix) {
		STATIC_FILE_URL_PREFIX = static_file_url_prefix;
	}
	
	static {
		//必须在所有的属性的最下面
		ConstansUtil.getPropertyValue("constants");//从constants.properties读取value，将常量重新赋值
	}
}
