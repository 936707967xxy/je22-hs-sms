package com.hoomsun.vo;

public class emailSendBeanReq {
	
	private String host; // smtp服务器
    private String from; // 发件人地址
    private String to; // 收件人地址
    private String affix; // 附件地址
    private String affixName; // 附件名称
    private String user; // 用户名
    private String pwd; // 密码
    private String title; // 邮件标题
    private String context;//邮件内容
    
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getAffix() {
		return affix;
	}
	public void setAffix(String affix) {
		this.affix = affix;
	}
	public String getAffixName() {
		return affixName;
	}
	public void setAffixName(String affixName) {
		this.affixName = affixName;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
}
