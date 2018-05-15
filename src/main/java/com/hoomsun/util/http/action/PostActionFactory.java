package com.hoomsun.util.http.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.hoomsun.util.message.LogCvt;

/**
 * Servlet动作工厂类-处理POST请求
 * 
 * @author zhupengcheng@f-road.com.cn
 * @date 2016-11-02
 *
 */
public class PostActionFactory extends ActionFactory {

	@Override
	protected Map<String, Class<?>> defaultMap() {
		Iterator<Object> iterator = ActionFactory.prop.keySet().iterator();
		Map<String, Class<?>> map = new HashMap<String, Class<?>>();
		while(iterator.hasNext()){
			String key = String.valueOf(iterator.next());
			String value = ActionFactory.prop.getProperty(key).trim();
			try {
				map.put(getActionName(key), Class.forName(value));
			} catch (ClassNotFoundException e) {
				LogCvt.error("类装载报错："+e.getMessage(),e);
			}
		}

		return map;
	}
}
