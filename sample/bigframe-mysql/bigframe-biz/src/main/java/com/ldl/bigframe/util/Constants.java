package com.ldl.bigframe.util;

public class Constants {
	
	
	public static String STATIC_FILE_URL_PREFIX = "";////��̬�ļ�URLǰ׺

	public String getSTATIC_FILE_URL_PREFIX() {
		return STATIC_FILE_URL_PREFIX;
	}

	public void setSTATIC_FILE_URL_PREFIX(String static_file_url_prefix) {
		STATIC_FILE_URL_PREFIX = static_file_url_prefix;
	}
	
	static {
		//���������е����Ե�������
		ConstansUtil.getPropertyValue("constants");//��constants.properties��ȡvalue�����������¸�ֵ
	}
}
