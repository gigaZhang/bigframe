package com.ldl.bigframe.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.BeanUtils;



public class ConstansUtil {
	@SuppressWarnings("unchecked")
	public static void getPropertyValue(String fileName) {
		ResourceBundle rb = ResourceBundle.getBundle(fileName);
		Map map = new HashMap();
		Constants constants = new Constants();
		Enumeration enums = rb.getKeys();
		while (enums.hasMoreElements()) {
			String key = (String) enums.nextElement();
			map.put(key, rb.getString(key.toUpperCase()));
		}
		
		try {
			BeanUtils.copyProperties(constants, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
