package com.hoomsun.vo;
/**
* <p>Title: NationalBusPublicHeadResp</p>  
* <p>Description: 全国公交查询公共系统报文头</p>  
* @author 
* @date 2018年4月10日
 */
public class PublicHeadResp {
	
	private String showapi_res_code;
	private String showapi_res_error;
	private Object showapi_res_body;
	
	public String getShowapi_res_code() {
		return showapi_res_code;
	}
	public void setShowapi_res_code(String showapi_res_code) {
		this.showapi_res_code = showapi_res_code;
	}
	public String getShowapi_res_error() {
		return showapi_res_error;
	}
	public void setShowapi_res_error(String showapi_res_error) {
		this.showapi_res_error = showapi_res_error;
	}
	public Object getShowapi_res_body() {
		return showapi_res_body;
	}
	public void setShowapi_res_body(Object showapi_res_body) {
		this.showapi_res_body = showapi_res_body;
	}
}
