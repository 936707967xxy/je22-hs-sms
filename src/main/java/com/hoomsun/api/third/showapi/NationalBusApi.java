package com.hoomsun.api.third.showapi;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hoomsun.util.ContantsApi;
import com.hoomsun.util.JSONUtil;
import com.hoomsun.util.message.LogCvt;
import com.hoomsun.util.message.ResBody;
import com.hoomsun.util.other.PropertiesUtil;
import com.hoomsun.util.showapi.showApiStatusUtils;
import com.hoomsun.vo.NationalBusResp;
import com.hoomsun.vo.PublicHeadResp;
import com.hoomsun.vo.req.NationalBusReq;
import com.show.api.ShowApiRequest;

public class NationalBusApi {
	
	/**
	 * <p>Copyright: Copyright (c) 2018</p>  
	 * <p>Company: www.hoomsun.com</p>
	 * @author
	 * @return
	 * @Description : 全国站点换乘线路查询
	 * @param 2018年4月9日
	 */
	public static ResBody queryNationalSiteTransferLine(NationalBusReq req){
		ResBody res=new ResBody();
		NationalBusResp busResp=new NationalBusResp();
		try {
			String url=PropertiesUtil.getValue(ContantsApi.URL1463_7);
			String appId=PropertiesUtil.getValue(ContantsApi.APP_ID);
			String appSecret=PropertiesUtil.getValue(ContantsApi.APP_SECRET);
			String re=new ShowApiRequest(url,appId,appSecret)
			.addTextPara("cityName",req.getCityName()).addTextPara("lineSiteName",req.getLineSiteName()).post();
			LogCvt.info("第三方响应参数==>"+re);
			PublicHeadResp publicResp=JSONUtil.toJavaObject(re, PublicHeadResp.class);
			Map<String,Object>line=(Map<String,Object>)JSONUtil.toMap(publicResp.getShowapi_res_body());
			Object[] ob=JSONObject.parseArray(line.get(ContantsApi.SHOWAPI_LINE).toString()).toArray();
			Map<String,Object>third=(Map<String,Object>)JSONUtil.toMap(ob[0]);
			
			busResp.setSiteName(String.valueOf(third.get("siteName")));
			busResp.setLinkNameList(String.valueOf(third.get("linkNameList")));
			res.setCode(String.valueOf(line.get("ret_code")));
			res.setMessage(showApiStatusUtils.getMess(publicResp.getShowapi_res_code().toString()));
			res.setData(busResp);
		} catch (Exception e) {
			// TODO: handle exception
			LogCvt.error("系统异常"+e.getMessage());
		}
		return res;
	}
	
	public static void main(String[] args) {
		NationalBusReq req=new NationalBusReq();
		req.setCityName("西安");
		req.setLineSiteName("小雁塔");
		ResBody res=queryNationalSiteTransferLine(req);
		System.out.println(JSON.toJSONString(res));
	}
}
