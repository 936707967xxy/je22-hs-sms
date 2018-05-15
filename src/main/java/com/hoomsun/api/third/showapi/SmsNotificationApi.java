package com.hoomsun.api.third.showapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import com.hoomsun.util.ContantsApi;
import com.hoomsun.util.JSONUtil;
import com.hoomsun.util.StringUtil;
import com.hoomsun.util.message.LogCvt;
import com.hoomsun.util.message.ResBody;
import com.hoomsun.util.other.PropertiesUtil;
import com.hoomsun.util.showapi.showApiStatusUtils;
import com.hoomsun.vo.PublicHeadResp;
import com.hoomsun.vo.req.SmsNotificationReq;
import com.hoomsun.vo.resp.SmsNotificationResp;
import com.show.api.ShowApiRequest;

/**
* <p>Title: SmsNotificationApi</p>  
* <p>Description: 短信通知</p>  
* @author xinyuan.xu@hoomsun.com  
* @date 2018年4月10日
 */
public class SmsNotificationApi {
	
	/**
	 * <p>Copyright: Copyright (c) 2018</p>  
	 * <p>Company: www.hoomsun.com</p>
	 * @author xinyuan.xu@hoomsun.com
	 * @param req
	 * @return
	 * @Description:创建自定义短信模板
	 * @param 2018年4月10日
	 */
	public static ResBody setSmsNotificationTemp(SmsNotificationReq req){
		ResBody res=new ResBody();
		SmsNotificationResp resp=new SmsNotificationResp();
		try {
			String url=PropertiesUtil.getValue(ContantsApi.URL_SMS_28_2);
			String appId=PropertiesUtil.getValue(ContantsApi.APP_ID);
			String appSecret=PropertiesUtil.getValue(ContantsApi.APP_SECRET);
			String result=new ShowApiRequest(url,appId,appSecret)
			.addTextPara("content",req.getContent())
			.addTextPara("title",req.getTitle())
			.addTextPara("notiPhone",req.getNotiPhone())
		    .post();
			
			LogCvt.info("第三方响应参数==>"+result);
			PublicHeadResp publicResp=JSONUtil.toJavaObject(result, PublicHeadResp.class);
			Map<String,Object>line=(Map<String,Object>)JSONUtil.toMap(publicResp.getShowapi_res_body());
			
			resp.setRet_code(line.get("ret_code").toString());
			resp.setRemark(line.get("remark").toString());
			resp.settNum(line.get("tNum").toString());
			
			res.setCode(resp.getRet_code());
			res.setMessage(showApiStatusUtils.getMess(resp.getRet_code()));
			res.setData(resp);
		} catch (Exception e) {
			// TODO: handle exception
			res.setCode("-1");
			res.setMessage("系统异常");
			LogCvt.error("系统异常"+e.getMessage());
		}
		return res;
	}
	
	/**
	 * <p>Copyright: Copyright (c) 2018</p>  
	 * <p>Company: www.hoomsun.com</p>
	 * @author xinyuan.xu@hoomsun.com
	 * @param req
	 * @return
	 * @Description:查询短信模板
	 * <p>1、自定义短信模板</p>
	 * <p>2、系统短信模板</p>
	 * @param 2018年4月10日
	 */
	public static ResBody querySmsNotificationTemplat(SmsNotificationReq req){
		ResBody res=new ResBody();
		List<SmsNotificationResp>pageDate=new ArrayList<SmsNotificationResp>();
		try {
			String url=PropertiesUtil.getValue(ContantsApi.URL_SMS_28_3);
			String appId=PropertiesUtil.getValue(ContantsApi.APP_ID);
			String appSecret=PropertiesUtil.getValue(ContantsApi.APP_SECRET);
			String result=new ShowApiRequest(url,appId,appSecret)
			.addTextPara("nomalTemplate",req.getNomalTemplate())
		    .post();
			SmsNotificationResp resp=null;
			LogCvt.info("第三方响应参数==>"+result);
			PublicHeadResp publicResp=JSONUtil.toJavaObject(result, PublicHeadResp.class);
			Map<String,Object>line=(Map<String,Object>)JSONUtil.toMap(publicResp.getShowapi_res_body());
			Object[] ob=JSONObject.parseArray(line.get(ContantsApi.SHOWAPI_TEMPLATES).toString()).toArray();
			if(ob!=null){
				for (int i = 0; i < ob.length; i++) {
					Map<String,Object>third=(Map<String,Object>)JSONUtil.toMap(ob[i]);
					resp=new SmsNotificationResp();
					resp.setTitle(String.valueOf(third.get("title")));
					resp.setContentTemp(String.valueOf(third.get("content")));
					resp.settNum(String.valueOf(third.get("tNum")));
					resp.setCreateDate(String.valueOf(third.get("ct")));
					pageDate.add(resp);
				}
				res.setCode(String.valueOf(line.get("ret_code")));
				res.setMessage(showApiStatusUtils.getMess(res.getCode()));
				res.setData(pageDate);
			}else{
				res.setCode("404");
				res.setMessage("无模板信息");
			}
		} catch (Exception e) {
			// TODO: handle exception
			res.setCode("-1");
			res.setMessage("系统异常");
			LogCvt.error("系统异常"+e.getMessage());
		}
		return res;
	}
	/**
	 * <p>Copyright: Copyright (c) 2018</p>  
	 * <p>Company: www.hoomsun.com</p>
	 * @author xinyuan.xu@hoomsun.com
	 * @param req
	 * @return
	 * @Description:发送短信
	 * @param 2018年4月11日
	 */
	public static ResBody sendSms(SmsNotificationReq req){
		ResBody res=new ResBody();
		try {
			//组装短信内容
			String url=PropertiesUtil.getValue(ContantsApi.URL_SMS_28_1);
			String appId=PropertiesUtil.getValue(ContantsApi.APP_ID);
			String appSecret=PropertiesUtil.getValue(ContantsApi.APP_SECRET);  
			String result=new ShowApiRequest(url,appId,appSecret)
			.addTextPara("mobile",req.getNotiPhone())
			.addTextPara("content",StringUtil.splitSmsTemplate(StringUtil.indexSmsTemp(req.getNomalTemplate()),req))
			.addTextPara("tNum",req.getNomalTemplate())
			.addTextPara("big_msg","")
		    .post();
			
			LogCvt.info("第三方响应参数==>"+result);
			PublicHeadResp publicResp=JSONUtil.toJavaObject(result, PublicHeadResp.class);
			Map<String,Object>line=(Map<String,Object>)JSONUtil.toMap(publicResp.getShowapi_res_body());
			
			res.setCode(String.valueOf(line.get("ret_code")));
			res.setMessage(showApiStatusUtils.getMess(res.getCode()));
			res.setData(line.get("taskID"));
		} catch (Exception e) {
			// TODO: handle exception
			res.setCode("-1");
			res.setMessage("系统异常");
			LogCvt.error(e.getMessage());
		}
		return res;
	}
	
	public static void main(String[] args) {
		
	}
}
