package com.tz.bms.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Beanfactory {
	private static Properties pros = new Properties();

	static {
		try {
			InputStream is = Beanfactory.class.getClassLoader()
					.getResourceAsStream("bean.properties");
			pros.load(is);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据名称,返回bean对象
	 */
	public static Object getBean(String name) {
		String value = pros.getProperty(name);
		Object obj = null;
		try {
			obj = Class.forName(value).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
