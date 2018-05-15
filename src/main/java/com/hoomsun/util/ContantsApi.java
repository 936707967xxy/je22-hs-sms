package com.hoomsun.util;

import java.util.HashMap;
import java.util.Map;

public class ContantsApi {
	
	public  static final  String SHOWAPI_LINE = "line";
	public  static final  String SHOWAPI_TEMPLATES = "templates";
	
	public  static final  String APP_ID = "appid";
	public  static final  String APP_SECRET = "appSecret";
	
	/**
	 * 全国站点换乘公交线路查询
	 */
	public  static final  String URL1463_7 = "URL1463_7";
	
	/****************************三网短信发送*******************************************/
	/**
	 * 发送短信URL
	 */
	public  static final  String URL_SMS_28_1 = "URL_SMS_28_1";
	/**
	 * 设置短信模板URL
	 */
	public  static final  String URL_SMS_28_2 = "URL_SMS_28_2";
	/**
	 * 查询短信模板URL
	 */
	public  static final  String URL_SMS_28_3 = "URL_SMS_28_3";
	
	/**
	 * 身份认证
	 */
	public static final String IDENTITY_AUTHENTICATION ="T150606060609";
	/**
	 * 用户注册
	 */
	public static final String USER_REGISTRATION ="T150606060601";
	/**
	 * 提醒操作
	 */
	public static final String OPERATION_REMINDING ="T150606060608";
	
	/**
	 * 初始化短信模板
	 */
	public static final Map<String,String>mapSms=new HashMap<String,String>();
	
	static{
		mapSms.put(IDENTITY_AUTHENTICATION, "{\"code\":\"@1@\",\"minute\":\"@2@\",\"comName\":\"@3@\"}");
		mapSms.put(USER_REGISTRATION, "{\"code\":\"@1@\",\"minute\":\"@2@\",\"comName\":\"@3@\"}");
		mapSms.put(OPERATION_REMINDING, "{\"opName\":\"@1@\",\"contactWay\":\"@2@\",\"comName\":\"@3@\"}");
	}
}
