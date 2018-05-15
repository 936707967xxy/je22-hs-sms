package com.hoomsun.util.other;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.hoomsun.util.message.LogCvt;

/**
 * 读取配置文件config.properties的内容
 */
public class SysConfig {

	public static final String ENCODING = "UTF-8";
	public static final String CONFIG_PATH = "/config.properties";
	public static String DIR_PATH=null;

	private Properties props = null;// config.properties

	/**
	 * 获取全局唯一的SysConfig 对象
	 * @return
	 */
	public static SysConfig getInstance() {
		SysConfig config = ConfigHolder.INSTANCE.getConf();
		config.init();
		return config;
	}

	/**
	 * 初始化
	 */
	private void init(){
		DIR_PATH=System.getProperty("dir");
		props = new Properties();
		loadConfigProps();
	}

	/**
	 * 加载配置文件
	 * 
	 * 2017-11-30   修改配置文件路径   刘栋梁  
	 */
	public void loadConfigProps() {
		InputStream is = null;
		try {
			
			is = getClass().getResourceAsStream(DIR_PATH+"/config/config.properties");
			if (is == null) {
				is = getClass().getResourceAsStream("/config.properties");
			}
			props.load(is);
		} catch (IOException e) {
			e.printStackTrace();
			LogCvt.error("load config.properties error!please check the file!", e);
		} finally {
			if (is != null) {
				try {
					is.close();
					is = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getProperty(String key) {
		String tmp = props.getProperty(key);
		if (StringUtils.isNotEmpty(tmp)) {
			return tmp.trim();
		}
		return tmp;
	}

	public boolean getPropertyBoolean(String key) {
		String tmp = props.getProperty(key);
		if (StringUtils.isNotEmpty(tmp)) {
			return Boolean.parseBoolean(tmp.trim());
		}
		return false;
	}

	public int getPropertyInt(String key) {
		String tmp = props.getProperty(key);
		if (StringUtils.isNotEmpty(tmp)) {
			return Integer.parseInt(tmp.trim());
		}
		return 0;
	}



	enum ConfigHolder {
		INSTANCE;
		SysConfig conf;

		ConfigHolder() {
			conf = new SysConfig();
		}
		public SysConfig getConf(){
			return conf;
		}
	}

	public static void main(String[] args) {

		if(System.getProperty("dir")==null){
			System.setProperty("dir", System.getProperty("user.dir"));
		}
		System.out.println(getInstance().getProperty("rest_server"));

	}



}
