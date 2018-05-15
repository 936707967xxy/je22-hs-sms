package com.hoomsun.vo;
/**
* <p>Title: SmsTemplateReq</p>  
* <p>Description: 发送短信</p>  
* @author xinyuan.xu@hoomsun.com  
* @date 2018年3月22日
 */
public class SmsTemplateReq {
	
	private String corpID;
	private String pwd;
	private String mobile;
	private String content;
	private String send_time;
	private String privateMd5;
	
	public String getCorpID() {
		return corpID;
	}
	public void setCorpID(String corpID) {
		this.corpID = corpID;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}
	public String getPrivateMd5() {
		return privateMd5;
	}
	public void setPrivateMd5(String privateMd5) {
		this.privateMd5 = privateMd5;
	}  
	@Override
	public String toString() {
		return "SmsTemplateReq [corpID=" + corpID + ", pwd=" + pwd
				+ ", mobile=" + mobile + ", content=" + content
				+ ", send_time=" + send_time + "]";
	}
}
