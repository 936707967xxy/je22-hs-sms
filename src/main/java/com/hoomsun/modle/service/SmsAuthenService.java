package com.hoomsun.modle.service;

import com.hoomsun.api.authentication.SmsAuthenManager;
import com.hoomsun.util.message.ResBody;
import com.hoomsun.vo.AuthenCodeReq;

/**
* <p>Title: SmsAuthenService</p>  
* <p>Description: 语音短信发送</p>  
* @author xinyuan.xu@hoomsun.com  
* @date 2018年3月23日
 */
public class SmsAuthenService {
	
	/**
	 * <p>Copyright: Copyright (c) 2018</p>  
	 * <p>Company: www.hoomsun.com</p>
	 * @author xinyuan.xu@hoomsun.com
	 * @param req
	 * @return
	 * @Description:发送语音短信
	 * @param 2018年3月23日
	 */
	public  ResBody send(AuthenCodeReq req){
		ResBody res=new ResBody();
		try {
		if(req!=null){
			String flag = SmsAuthenManager.TemplateSMS(
						req.isMessType(),req.getAccountSid(), req.getAuthToken(), req.getAppId(), 
						req.getTemplateId(), req.getMobile(), req.getParam());
				res.setCode(ResBody.SUCCESS_CODE);
				res.setData("flag:"+flag);
				res.setMessage("短信发送成功");
		}else{
			res.setCode(ResBody.ERROR_CODE);
			res.setMessage("请求参数不能为空");
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			res.setCode(ResBody.ERROR_CODE);
			res.setMessage("短信发送失败");
			e.printStackTrace();
		}
		return res;
	}
}
