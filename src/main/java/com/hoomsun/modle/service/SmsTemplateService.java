package com.hoomsun.modle.service;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import com.hoomsun.api.template.SmsTemplateManager;
import com.hoomsun.util.Constants;
import com.hoomsun.util.encryption.Base64;
import com.hoomsun.util.encryption.Md5;
import com.hoomsun.util.message.LogCvt;
import com.hoomsun.util.message.ResBody;
import com.hoomsun.util.other.PropertiesUtil;
import com.hoomsun.vo.SmsTemplateReq;

/**
* <p>Title: SmsTemplateService</p>  
* <p>Description: 发送短信</p>  
* @author xinyuan.xu@hoomsun.com  
* @date 2018年3月22日
 */
public class SmsTemplateService {
	/**
	 * Hppt POST请求发送方法 返回值>0 为 提交成功
	 * @param CorpID账号
	 * @param Pwd密码
	 * @param Mobile电话号码
	 * @param Content发送内容
	 * @param send_time定时发送时间，为空时，为及时发送
	 * @return
	 */
	public ResBody send(SmsTemplateReq req){
		ResBody res=new ResBody();
		try {
			String corpID=PropertiesUtil.getValue("corpID");
			String pwd=PropertiesUtil.getValue("Pwd");
			//加密报文
			String md5=Md5.getResult(req.toString());
			LogCvt.info("加密报文"+md5);
//			if(!md5.equals(req.getPrivateMd5())){
//				LogCvt.error("报文体信息被篡改，不能继续执行......");
//				res.setCode(ResBody.ERROR_CODE);
//				res.setMessage("报文体信息被篡改，不能继续执行......");
//				return res;
//			}
			String md5Key=PropertiesUtil.getValue(Constants.SECRET_MD5_KEY).trim();
			ResBody derypt=Base64.decryptObject(md5Key, req.getContent());
//			if(ResBody.ERROR_CODE.equals(derypt.getCode())){
//				return derypt;
//			}
			LogCvt.info("报文解密："+derypt.getData().toString());
			int code = SmsTemplateManager.sendSMSPost(corpID, pwd, req.getMobile(),req.getContent(), req.getSend_time());
			if(code>0){
				res.setCode(ResBody.SUCCESS_CODE);
				res.setMessage("发送成功");
			}else{
				res.setCode(ResBody.ERROR_CODE);
				res.setMessage("发送失败");
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			res.setCode(ResBody.ERROR_CODE);
			res.setMessage("URL参数不合法");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			res.setCode(ResBody.ERROR_CODE);
			res.setMessage("不支持字符编码");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogCvt.error("报文解析异常");
			res.setCode(ResBody.ERROR_CODE);
			res.setMessage("报文解析异常");
			e.printStackTrace();
		}
		return res;
	}
}
