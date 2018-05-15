package com.hoomsun.vo.req;
/**
* <p>Title: NationalBusReq</p>  
* <p>Description: 全国公交查询请求参数</p>  
* @author 
* @date 2018年4月10日
 */
public class NationalBusReq {
	
	private String cityName;
	private String lineSiteName;
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getLineSiteName() {
		return lineSiteName;
	}
	public void setLineSiteName(String lineSiteName) {
		this.lineSiteName = lineSiteName;
	}
}
