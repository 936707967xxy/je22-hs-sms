package com.hoomsun.api.authentication;


import com.alibaba.fastjson.JSONObject;
import com.hoomsun.util.other.JsonReqClient;
import com.hoomsun.util.other.XmlReqClient;
import com.hoomsun.vo.AuthenCodeReq;

/**
* <p>Title: SmsAuthenManager</p>  
* <p>Description:
* <li>1、明文短信验证码</li>
* <li>2、语音短信验证码</li>
*  </p>  
* @author xinyuan.xu@hoomsun.com  
* @date 2018年3月12日
 */
public class SmsAuthenManager {

	
	
	/**
	 * 发短信接口方法
	 * 
	 * @param b
	 * @param accountSid
	 * @param authToken
	 * @param appId
	 * @param templateId
	 * @param to
	 * @param param
	 * @return
	 */
	public static String TemplateSMS(boolean b, String accountSid, String authToken, String appId, String templateId, String to, String param) throws Exception{

		String result = null;
		if (b) {
			result = new JsonReqClient().templateSMS(accountSid, authToken, appId, templateId, to, param);
		} else {
			result = new XmlReqClient().templateSMS(accountSid, authToken, appId, templateId, to, param);
		}
		// 发送短信
		JSONObject object=JSONObject.parseObject(result);
		JSONObject resp = (JSONObject) object.get("resp");
		String code = resp.get("respCode").toString();
		// 发送状态
		String flag = null;
		if ("000000".equals(code)) { // 成功
			flag = "0";
		} else { // 失败
			flag = "1";
		}
		return flag;
	}

	/**
	 * <p>Copyright: Copyright (c) 2018</p>  
	 * <p>Company: www.hoomsun.com</p>
	 * @author xinyuan.xu@hoomsun.com
	 * @param accountSid
	 * @param authToken
	 * @param appId
	 * @param to
	 * @param verifyCode
	 * @return
	 * @Description:
	 * <li> 语音发送</li>
	 * <li> accountSid 企业id authToken 企业标识 appId 模板id to 手机号 verifyCode</li>
	 * @param 2018年3月12日
	 */
	public static String voiceCode(String accountSid, String authToken, String appId, String to, String verifyCode) {
		// TODO Auto-generated method stub
		String result = null;
		result = new JsonReqClient().voiceCode(accountSid, authToken, appId, to, verifyCode);
		return result;
	}

	
	/*public static void main(String[] args) {

		String phone = "13631238895";
		// String verifyCode=(String)request.getSession().getAttribute(phone);
		String verifyCode = "123456";
		String accountSid = "138bc91472ac5b5192195669d9246d71";
		// Auth Token
		String token = "b85406e6f8c9ba1a6dec6d34a66bb52e";
		// appId
		String appId = "975c95443bf24c4fa6b8782557800df1";

		String resultString = voiceCode(accountSid, token, appId, phone, verifyCode);
		System.out.println(resultString);

	}*/

	public static void main(String[] args) {
		AuthenCodeReq req=new AuthenCodeReq();
//		req.setMessType(true);
//		// Account Sid
String accountSid = "138bc91472ac5b5192195669d9246d71";
//		req.setAccountSid("138bc91472ac5b5192195669d9246d71");
//		// Auth Token
String token = "b85406e6f8c9ba1a6dec6d34a66bb52e";
//		req.setToken("b85406e6f8c9ba1a6dec6d34a66bb52e");
//		// appId
String appId = "775db3d12adf4bcf9e9d5f576e11d74d";
//		req.setAppId("775db3d12adf4bcf9e9d5f576e11d74d");
//		// 短信模板templateId
String templateId = "129022";
//		req.setTemplateId("129022");
//		// 参数
String to = "13631238895";
//		req.setTelephone("13631238895");
//		//String para =  "654321";
//		req.setParamCode("654321");
		
	
	}

}
