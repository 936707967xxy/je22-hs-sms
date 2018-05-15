package com.hoomsun.modle.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hoomsun.util.http.action.Action;
import com.hoomsun.util.message.LogCvt;
import com.hoomsun.util.message.ResBody;
import com.hoomsun.util.other.RequestUtil;
import com.hoomsun.vo.AuthenCodeReq;
/**
* <p>Title: SmsAuthenAction</p>  
* <p>Description:语音短信发送 </p>  
* @author xinyuan.xu@hoomsun.com  
* @date 2018年3月23日
 */
public class SmsAuthenAction implements Action{

	public ResBody perform(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		ResBody res=null;
		try{
			AuthenCodeReq req = (AuthenCodeReq) RequestUtil.copyParam(AuthenCodeReq.class, request);
			LogCvt.info("请求参数(JSON)："+JSON.toJSONString(req));
			return null;
		}catch(Exception e){
			res=new ResBody();
			res.setCode(ResBody.ERROR_CODE);
			res.setMessage("系统异常");
		}
		return res;
	}
	
}
