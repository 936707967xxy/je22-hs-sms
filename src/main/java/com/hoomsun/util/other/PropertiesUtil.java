package com.hoomsun.util.other;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class PropertiesUtil {
	public static final String FILE_NAME = "init.properties";
	public static Properties prop = new Properties();
	static {
		InputStream in = ConfigLoad.loadAsStream(FILE_NAME);
		prop = new Properties();
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(in, "utf-8"));
			prop.load(bf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String getValue(String key){
		if(StringUtils.isNotBlank(prop.getProperty(key))){
			return prop.getProperty(key);
		}
		return "";
	}
	public static void main(String[] args) {
		System.out.println(getValue("name"));
	}
}
