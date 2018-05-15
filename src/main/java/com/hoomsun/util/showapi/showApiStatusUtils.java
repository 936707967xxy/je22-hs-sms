package com.hoomsun.util.showapi;

import java.util.HashMap;
import java.util.Map;


/**
* <p>Title: NationalBusEnum</p>  
* <p>Description: </p>  
* @author 
* @date 2018年4月10日
 */
public class showApiStatusUtils {
	
	public static final Map<String,String>map=new HashMap<String,String>();
	
	static{
		map.put("0","成功");
		map.put("-1","系统调用错误");
		map.put("-2","可调用次数或金额为0");
		map.put("-3","读取超时");
		map.put("-4","服务端返回数据解析错误");
		map.put("-5","后端服务器DNS解析错误");
		map.put("-6","服务不存在或未上线");
		map.put("-1000","系统维护");
		map.put("-1002","showapi_appid字段必传");
		map.put("-1003","showapi_sign字段必传");
		map.put("-1004","签名sign验证有误");
		map.put("-1005","showapi_timestamp无效");
		map.put("-1006","app无权限调用接口 ");
		map.put("-1007","没有订购套餐");
		map.put("-1008","服务商关闭对您的调用权限");
		map.put("-1009","调用频率受限");
		map.put("-1010","找不到您的应用");
		map.put("-1011","子授权app_child_id无效");
		map.put("-1012","子授权已过期或失效");
		map.put("-1013","子授权ip受限");
	}
	
	
	public static String getMess(String code){
		return map.get(code);
	}
	
	public static void main(String[] args) {
		System.out.println(showApiStatusUtils.getMess("-1"));
	}
}
