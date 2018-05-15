package com.hoomsun.vo;


/**
* <p>Title: NationalBusResp</p>  
* <p>Description: 全国公交查询响应对象</p>  
* @author 
* @date 2018年4月9日
 */
public class NationalBusResp {
	
    private String linkNameList;
	private String siteName;
	
	public String getLinkNameList() {
		return linkNameList;
	}
	public void setLinkNameList(String linkNameList) {
		this.linkNameList = linkNameList;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
}
