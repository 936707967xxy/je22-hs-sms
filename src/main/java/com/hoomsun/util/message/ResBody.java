package com.hoomsun.util.message;

import com.alibaba.fastjson.JSON;

/**
 * 返回Json对象
* <p>Title: ResBody</p>  
* <p>Description: </p>  
* @author xinyuan.xu@hoomsun.com  
* @date 2018年3月6日
 */
public class ResBody {
	
	public static final String SUCCESS_CODE = "0000";
	public static final String ERROR_CODE = "9999";
	String code = "0000";
	String message = "成功";
	Object data;

	public ResBody() {
	}

	public ResBody(String paramString1, String paramString2) {
		this.message = paramString2;
		this.code = paramString1;
	}

	public static ResBody createErrorResBody(String paramString) {
		return new ResBody(ERROR_CODE, paramString);
	}

	public static ResBody createErrorResBody(String paramString,
			Object paramObject) {
		ResBody localResBody = new ResBody(ERROR_CODE, paramString);
		localResBody.setData(paramObject);
		return localResBody;
	}

	public ResBody(Object paramObject) {
		this.code = ResultEnum.SUCESS.getCode();
		this.message = ResultEnum.SUCESS.getMsg();
		this.data = paramObject;
	}

	public static ResBody result(Object paramObject) {
		return new ResBody(paramObject);
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String paramString) {
		this.code = paramString;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String paramString) {
		this.message = paramString;
	}

	public Object getData() {
		return this.data;
	}

	public void setData(Object paramObject) {
		this.data = paramObject;
	}

	public  String toJsonString() {
		return JSON.toJSONString(this);
	}
}
