package com.hoomsun.util.other;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.hoomsun.util.Constants;
import com.hoomsun.util.message.LogCvt;

/**
 * 读取配置文件工具类
 * 
 * @author 冯理罡
 *
 */
public final class ConfigLoad {

	/**
	 * 读取配置文件流
	 * 
	 * @param filename
	 * @return
	 */
	public static InputStream loadAsStream(String filename) {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
		try {
			if (null == in) {
				File file = new File(Constants.CONFIG_URI + filename);
				if (!file.exists()) {
					throw new FileNotFoundException("没有找到配置文件：" + filename);
				}
				in = new FileInputStream(new File(Constants.CONFIG_URI + filename));

			}
		} catch (FileNotFoundException e) {
			LogCvt.error("无法获取配置文件.", e);
		} 
		return in;
	}
}
