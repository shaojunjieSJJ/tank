package com.mashibing.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
	
	public static final Properties props = new Properties(); 
	
	private PropertyMgr() {
		
	}
	static {
		try {
			props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Object get(String key) {
		if (props == null) return null;
		return props.get(key);
	}
	
	public static Integer getInt(String key) {
		if (props == null) return null;
		return Integer.parseInt((String)props.get(key));
	}
	
	public static String getString(String key) {
		if (props == null) return null;
		return (String)props.get(key);
	}
	
}
