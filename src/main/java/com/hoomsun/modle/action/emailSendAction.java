package com.hoomsun.modle.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hoomsun.modle.service.emailSendService;
import com.hoomsun.util.http.action.Action;
import com.hoomsun.util.message.LogCvt;
import com.hoomsun.util.message.ResBody;
import com.hoomsun.util.other.RequestUtil;
import com.hoomsun.vo.emailSendBeanReq;
/**
* <p>Title: emailSendAction</p>  
* <p>Description: 邮件发送</p>  
* @author xinyuan.xu@hoomsun.com  
* @date 2018年3月22日
 */
public class emailSendAction implements Action{
	
	private emailSendService service=new emailSendService();

	public ResBody perform(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		ResBody res=null;
		try{
			emailSendBeanReq req = (emailSendBeanReq) RequestUtil.copyParam(emailSendBeanReq.class, request);
			LogCvt.info("请求参数(JSON)："+JSON.toJSONString(req));
			return service.sendEmail(req);
		}catch(Exception e){
			res=new ResBody();
			res.setCode(ResBody.ERROR_CODE);
			res.setMessage("系统异常");
		}
		return res;
	}
}
