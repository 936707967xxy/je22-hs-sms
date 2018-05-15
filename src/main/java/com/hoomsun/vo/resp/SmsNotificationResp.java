package com.hoomsun.vo.resp;
/**
* <p>Title: SmsNotificationResp</p>  
* <p>Description: 发送短信响应参数</p>  
* @author xinyuan.xu@hoomsun.com  
* @date 2018年4月10日
 */
public class SmsNotificationResp {
	
	/**
	 * 成功标记 0 成功 其他失败
	 */
	private String ret_code;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 在平台创建好的模板id
	 */
	private String tNum;
	/**
	 * 创建时间
	 */
	private String createDate;
	/**
	 * 短信模板标题
	 */
	private String title;
	/**
	 * 短信模板内容
	 */
	private String contentTemp;
	
	public String getRet_code() {
		return ret_code;
	}
	public void setRet_code(String ret_code) {
		this.ret_code = ret_code;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String gettNum() {
		return tNum;
	}
	public void settNum(String tNum) {
		this.tNum = tNum;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContentTemp() {
		return contentTemp;
	}
	public void setContentTemp(String contentTemp) {
		this.contentTemp = contentTemp;
	}
}
