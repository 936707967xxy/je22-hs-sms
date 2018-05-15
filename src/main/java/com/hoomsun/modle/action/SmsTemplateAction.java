package com.hoomsun.modle.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hoomsun.modle.service.SmsTemplateService;
import com.hoomsun.util.http.action.Action;
import com.hoomsun.util.message.LogCvt;
import com.hoomsun.util.message.ResBody;
import com.hoomsun.util.other.RequestUtil;
import com.hoomsun.vo.SmsTemplateReq;
/**
* <p>Title: SmsTemplateAction</p>  
* <p>Description: 模板短信发送</p>  
* @author xinyuan.xu@hoomsun.com  
* @date 2018年3月22日
 */
public class SmsTemplateAction implements Action{
	
	private SmsTemplateService service=new SmsTemplateService();

	public ResBody perform(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		ResBody res=null;
		try{
			SmsTemplateReq req = (SmsTemplateReq) RequestUtil.copyParam(SmsTemplateReq.class, request);
			LogCvt.info("请求参数(JSON)："+JSON.toJSONString(req));
			req.setSend_time("");//为空表示立即发送
			return service.send(req);
		}catch(Exception e){
			res=new ResBody();
			res.setCode(ResBody.ERROR_CODE);
			res.setMessage("系统异常");
		}
		return res;
	}
	
}
