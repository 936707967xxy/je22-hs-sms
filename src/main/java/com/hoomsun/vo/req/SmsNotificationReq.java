package com.hoomsun.vo.req;
/**
* <p>Title: SmsNotificationReq</p>  
* <p>Description: 短信发送请求参数</p>  
* @author xinyuan.xu@hoomsun.com  
* @date 2018年4月10日
 */
public class SmsNotificationReq {
	
	/**
	 * 短信内容
	 */
	private String content;
	/**
	 * 短信标题
	 */
	private String title;
	/**
	 * 电话号码
	 */
	private String notiPhone;
	/**
	 * 模板类型:1.系统内置模板 2.自定义模板(自建模板)
	 */
	private String nomalTemplate;
	
	/**
	 * 填充短信模板变量 value1，value2，value3，value4
	 */
	private String value1;
	private String value2;
	private String value3;
	private String value4;
	
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNotiPhone() {
		return notiPhone;
	}
	public void setNotiPhone(String notiPhone) {
		this.notiPhone = notiPhone;
	}
	public String getNomalTemplate() {
		return nomalTemplate;
	}
	public void setNomalTemplate(String nomalTemplate) {
		this.nomalTemplate = nomalTemplate;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public String getValue3() {
		return value3;
	}
	public void setValue3(String value3) {
		this.value3 = value3;
	}
	public String getValue4() {
		return value4;
	}
	public void setValue4(String value4) {
		this.value4 = value4;
	}
}
