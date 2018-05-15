package com.hoomsun.modle.service;

import com.hoomsun.api.email.eMailUtil;
import com.hoomsun.util.message.LogCvt;
import com.hoomsun.util.message.ResBody;
import com.hoomsun.util.other.PropertiesUtil;
import com.hoomsun.vo.emailSendBeanReq;

/**
* <p>Title: emailSendService</p>  
* <p>Description: 邮件发送</p>  
* @author xinyuan.xu@hoomsun.com  
* @date 2018年3月20日
 */
public class emailSendService {
	/***
	 * <p>Copyright: Copyright (c) 2018</p>  
	 * <p>Company: www.hoomsun.com</p>
	 * @author xinyuan.xu@hoomsun.com
	 * @param req
	 * @return
	 * @throws Exception
	 * @Description:发送邮件
	 * @param 2018年3月22日
	 */
	public ResBody sendEmail(emailSendBeanReq req){
		ResBody res=new ResBody();
		try{
			String from=PropertiesUtil.getValue("emailAddress");
			String host=PropertiesUtil.getValue("emailHost");
			String user=PropertiesUtil.getValue("emailUser");
			String pwd=PropertiesUtil.getValue("emailPassword");
			eMailUtil cn = new eMailUtil();
	         // 设置发件人地址、收件人地址和邮件标题
		    cn.setAddress(from, req.getTo(),req.getTitle());//发件人的邮箱,收件地址,邮箱标题
		    LogCvt.info("开始发送邮件......");
		    cn.send(host, user, pwd,req.getContext());
		    LogCvt.info("发送邮件通知【"+req.getTo()+"】成功......");
		    res.setCode(ResBody.SUCCESS_CODE);
		    res.setMessage("发送成功");
		}catch(Exception e){
			res.setCode(ResBody.ERROR_CODE);
		    res.setMessage("发送失败");
		}
	    return res;
	}
}
