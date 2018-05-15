package com.hoomsun.util.other;
 
 
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.hoomsun.util.message.LogCvt;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class XmlUtil {

    
    private static final String ENCODING = "utf-8";
//	private static final Object SoapenvEnvelope = null;
	private static Map<String, Unmarshaller> xStreamInstance = new HashMap<String, Unmarshaller>(); 
	private static Map<Class<?>, XStream> xStreamInstance1 = new HashMap<Class<?>, XStream>(); 
	
	@SuppressWarnings("unchecked")
	public static <T> T xml2Bean(String xml,Class<T> clazz) throws Exception {
		 T t = null;  
	        try {  
	        	if(StringUtils.isEmpty(xml)){return null;}
	        	Unmarshaller unmarshaller=xStreamInstance.get(clazz.getName());
	        	if(unmarshaller==null){
	        		JAXBContext context = JAXBContext.newInstance(clazz); 
	        		unmarshaller = context.createUnmarshaller();  
	        		xStreamInstance.put(clazz.getName(), unmarshaller);
	        	}
	        	
	        	synchronized (unmarshaller) {
	        		t = (T) unmarshaller.unmarshal(new StringReader(xml)); 	
				} 
	        	
	        } catch (Exception e) { 
	        	LogCvt.error(e.getMessage(), e); 
	        	throw e;
	           
	        }  
	      return t;  
	} 
	
	@SuppressWarnings("unchecked")
	public static <T> T xml2Bean(String xx,String xml,Class<T> clazz) {
	    T t=null;
		try {  
			if(StringUtils.isEmpty(xml)){return null;}
			XStream xStream = xStreamInstance1.get(clazz);
			if(xStream == null){
				xStream = new XStream(new DomDriver(ENCODING));
				xStream.ignoreUnknownElements();
				xStream.alias("xml", clazz);
				xStreamInstance1.put(clazz, xStream);
			}
			 t = (T) xStream.fromXML(xml);
	    }catch(Exception e){
	    	LogCvt.error(e.getMessage(), e); 
	    }
		return t;
	}
	
	  public static String convertToXml(Object obj) {  
	        return convertToXml(obj, ENCODING);  
	  }  
	  
	    public static String convertToXml(Object obj, String encoding) {  
	        String result = null;  
	        try {  
	            JAXBContext context = JAXBContext.newInstance(obj.getClass());  
	            Marshaller marshaller = context.createMarshaller();  
	            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
	            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);  
	            StringWriter writer = new StringWriter();  
	            marshaller.marshal(obj, writer);  
	            result = writer.toString();  
	        } catch (Exception e) {  
	        	 LogCvt.error(e.getMessage(), e); 
	        }  
	  
	        return result;  
	    }  
	
	
	    public static Map<String,Object> getMapFromXML(String xmlString) throws ParserConfigurationException, IOException, SAXException {
			InputStream is = null;
			try{
		        //这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
		        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		        DocumentBuilder builder = factory.newDocumentBuilder();
		        is = XmlUtil.getStringStream(xmlString);
		        Document document = builder.parse(is);
		
		        //获取到document里面的全部结点
		        NodeList allNodes = document.getFirstChild().getChildNodes();
		        Node node;
		        Map<String, Object> map = new HashMap<String, Object>();
		        int i=0;
		        while (i < allNodes.getLength()) {
		            node = allNodes.item(i);
		            if(node instanceof Element){
	                map.put(node.getNodeName(),node.getTextContent());
	            }
		            i++;
		        }
		        return map;
			}finally {
				if( is != null){
					is.close();
				}
			}

	    }


    

    public static InputStream getStringStream(String sInputString) throws UnsupportedEncodingException {
        ByteArrayInputStream tInputStringStream = null;
        if (sInputString != null && !sInputString.trim().equals("")) {
            tInputStringStream = new ByteArrayInputStream(sInputString.getBytes("UTF-8"));
        }
        return tInputStringStream;
    }
   
    
//    public static void main(String[] args) throws Exception {
//    	String orderQueryServiceResponseString="<xml><AppId><![CDATA[wxbe4fef1a91ac3576]]></AppId><CreateTime>1503994606</CreateTime><InfoType><![CDATA[component_verify_ticket]]></InfoType><ComponentVerifyTicket><![CDATA[ticket@@@zBkeIrMW7xe4Mm_-JQMg2qM8KdxEIxyys_ZqdCpnGSxiyxOiCJ8VW7rZQznEN-PdybbmrBV3EXqj-WId2hb0wg]]></ComponentVerifyTicket></xml>";
//    	 ApiComponentVerifyTicketApiResp componentVerifyTicket = (ApiComponentVerifyTicketApiResp)Util.xml2Bean(orderQueryServiceResponseString, ApiComponentVerifyTicketApiResp.class);
//    	 System.out.println(componentVerifyTicket.getAppId());
//    }   	
    	//    	//OrderQueryRespData orderQueryResData = (OrderQueryRespData) Util.getObjectFromXML(orderQueryServiceResponseString, OrderQueryRespData.class);
//    	//LogCvt.debug(orderQueryResData.getResult_code());
//    	long l=System.currentTimeMillis();
//    	for(int i=0;i<100000;i++){
////    		new Thread(
////				new Runnable() {
////					public void run() {
//						String notifyString="<xml><appid><![CDATA[wx2421b1c4370ec43b]]></appid><attach><![CDATA[支付测试]]></attach><bank_type><![CDATA[CFT]]></bank_type><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[10000100]]></mch_id><nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str><openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid><out_trade_no><![CDATA[2017072488800007]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign><sub_mch_id><![CDATA[10000100]]></sub_mch_id><time_end><![CDATA[20140903131540]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id></xml>";
//			        	WeiXinNotifyData	notifyData = (WeiXinNotifyData)xml2Bean(notifyString,WeiXinNotifyData.class);
//			        	//System.out.println(notifyData.toString());
//			        	
//			        	notifyData = (WeiXinNotifyData)xml2Bean(notifyString,WeiXinNotifyData.class);
//			        	//System.out.println(notifyData.toString());
////					}
////				}
////    		).start();
//    	}
//    	System.out.println(System.currentTimeMillis()-l);
//    	l=System.currentTimeMillis();
//       	for(int i=0;i<100000;i++){
////    		new Thread(
////				new Runnable() {
////					public void run() {
//						String notifyString="<xml><appid><![CDATA[wx2421b1c4370ec43b]]></appid><attach><![CDATA[支付测试]]></attach><bank_type><![CDATA[CFT]]></bank_type><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[10000100]]></mch_id><nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str><openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid><out_trade_no><![CDATA[2017072488800007]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign><sub_mch_id><![CDATA[10000100]]></sub_mch_id><time_end><![CDATA[20140903131540]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id></xml>";
//			        	WeiXinNotifyData	notifyData = (WeiXinNotifyData)xml2Bean(notifyString,WeiXinNotifyData.class);
//			        	//System.out.println(notifyData.toString());
//			        	
//			        	notifyData = (WeiXinNotifyData)xml2Bean("xml",notifyString,WeiXinNotifyData.class);
//			        	//System.out.println(notifyData.toString());
////					}
////				}
////    		).start();
//    	}
//       	System.out.println(System.currentTimeMillis()-l);
//    	
//    	
////    	
////    	for(int i=0;i<1000;i++){
////    		new Thread(
////				new Runnable() {
////					public void run() {
////						String notifyString="<xml><appid><![CDATA[wx2421b1c4370ec43b]]></appid><attach><![CDATA[支付测试]]></attach><bank_type><![CDATA[CFT]]></bank_type><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[10000100]]></mch_id><nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str><openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid><out_trade_no><![CDATA[2017072488800007]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign><sub_mch_id><![CDATA[10000100]]></sub_mch_id><time_end><![CDATA[20140903131540]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id></xml>";
////			        	WeiXinNotifyData	notifyData = (WeiXinNotifyData)xml2Bean(notifyString,WeiXinNotifyData.class);
////			        	System.out.println(notifyData.toString());
////					}
////				}
////    		).start();
////    	}
//    	
//    	//String notifyString="<xml><appid><![CDATA[wx2421b1c4370ec43b]]></appid><attach><![CDATA[支付测试]]></attach><bank_type><![CDATA[CFT]]></bank_type><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[10000100]]></mch_id><nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str><openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid><out_trade_no><![CDATA[2017072488800007]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign><sub_mch_id><![CDATA[10000100]]></sub_mch_id><time_end><![CDATA[20140903131540]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id></xml>";
//    	//WeiXinNotifyData	notifyData = (WeiXinNotifyData)xml2Bean(notifyString,WeiXinNotifyData.class);
//    	//System.out.println(notifyData.toString());
//	}
    
    

//    public static void main(String[] args) throws Exception {
//		
//    	String str="<soapenv:Envelope  xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:in=\"http://www.ahrcu.com/common/header/in\" xmlns:r09=\"http://www.ahrcu.com/service/bd/R09902005971\">"+
//    			   "<soapenv:Header>"+
//        "<in:sysHeader>"+
//           "<msgId>100</msgId>"+
//           "<msgDate>200</msgDate>"+
//       "</in:sysHeader>"+
//     "</soapenv:Header>"+
//     "<soapenv:Body>"+
//        "<r09:request>"+
//           "<bizHeader>"+
//              "<authCode>123456</authCode>"+
//              "<channel>00</channel>"+
//           "</bizHeader>"+
//           "<bizBody>"+
//              "<devBy>34</devBy>"+
//              "<subMerchantId>555555</subMerchantId>"+
//           "</bizBody>"+
//        "</r09:request>"+
//     "</soapenv:Body>"+
//  "</soapenv:Envelope>";
//    	
//    	String reqXml = str;
//		reqXml = reqXml.substring(reqXml.indexOf("<soapenv:Body>"),reqXml.indexOf("</soapenv:Body>")+15);    	
//		
//		String reqHeaderXml = reqXml.substring(reqXml.indexOf("<bizHeader>"),reqXml.indexOf("</bizHeader>")+12);
//		String reqBodyXml = reqXml.substring(reqXml.indexOf("<bizBody>"),reqXml.indexOf("</bizBody>")+10);
//		
//		Map<String,Object>  headerMap  = XmlUtil.getMapFromXML(reqHeaderXml);
//    	Map<String,Object>  bodyMap  = XmlUtil.getMapFromXML(reqBodyXml);
//    	Map<String,Object> map = new HashMap<String, Object>();
//    	map.putAll(headerMap);
//    	map.putAll(bodyMap);
//    	//转成json,再转成javabean
//    	String jsonXml = JSONUtil.toJSonString(map);
//    	System.out.println("转成json为："+jsonXml);
//    	
////    	ReqBizBody info = (ReqBizBody) XmlUtil.xml2Bean(str, ReqBizBody.class);
////    	
////    	System.out.println(JSONUtil.toJSonString(info));
//
//
//    	
////    	RespHeader respHeader = new RespHeader();
////    	RespSysHeader respSysHeader = new RespSysHeader();
////    	respSysHeader.setMsgId("11111");
////    	respHeader.setRespSysHeader(respSysHeader);
////    	
////    	RespBodyBizHeader respBizHeader = new RespBodyBizHeader();
////    	respBizHeader.setAuthCode("88888");
////    	respBizHeader.setChannel("00");
////    	
////    	TestBodyBizBod respBizBod = new TestBodyBizBod();
////    	respBizBod.setCode("AAAAA");
////    	respBizBod.setMessage("交易成功");
////    	respBizBod.setTest("2323");
////    	RespBodyResponse response = new RespBodyResponse(respBizHeader,respBizBod);
////    	RespBody respBody = new RespBody(response);
////    	RespSoapEnvelope respSoapEnvelope = new RespSoapEnvelope();
////    	
////    	respSoapEnvelope.setRespBody(respBody);
////    	respSoapEnvelope.setRespHeader(respHeader);
////    	respSoapEnvelope.setR09("http://www.ahrcu.com/service/bd/R09902005971");
////    	String xml = XmlUtil.convertToXml(respSoapEnvelope);
////    	System.out.println(xml);
////    	
//    	
//    	
////    	String  xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:in=\"http://www.ahrcu.com/common/header/in\" xmlns:r09=\"http://www.ahrcu.com/service/bd/R09902005979\"><soapenv:Header>12323</soapenv:Header></soapenv:Envelope>";
////
////    	Text info = (Text) Util.xml2Bean(xml, Text.class);
////    	System.out.println(JSONUtil.toJSonString(info));
//    	
////    	
////    	
////    	
////    	JaxbBinder jaxbBinder = new JaxbBinder(SoapEnvelope.class);
////
//////        URL url = this.getClass().getClassLoader().getResource("receB2COrderResponse.xml");
////         String xml = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"+
////"<soap:Body>"+
////    "<ns1:receiveB2COrderResponse xmlns:ns1=\"http://www.sdo.com/mas/api/receive/\">"+
////        "<return>"+
////            "<customerName>爱奇艺</customerName>"+
////            "<customerNo>332396</customerNo>"+
////            
////        "</return>"+
////   "</ns1:receiveB2COrderResponse>"+
////"</soap:Body>"+
////"</soap:Envelope>";
////
////
////         SoapEnvelope envelope =jaxbBinder.fromXml(xml);
////
//////         Assert.notNull(envelope,"envelope数据为空");
//////         Assert.notNull(envelope.getBody(),"body数据为空");
//////         Assert.notNull(envelope.getBody().getReceB2COrderResponseList(),"body.list数据为空");
//////         Assert.notNull(envelope.getBody().getReceB2COrderResponseList().get(0),"body.list[0]数据为空");
//////         Assert.notNull(envelope.getBody().getReceB2COrderResponseList().get(0).getOrderNo(),"body.list[0]数据为空");
//////         System.out.println("getOrderNo="+envelope.getBody().getReceB2COrderResponseList().get(0).getOrderNo());
////
////         System.out.println("\ndata="+JSONUtil.toJSonString(envelope));
//    	
//	}
    
   
    
    public static String getStringFromMap(Map<String, Object> map, String key, String defaultValue) {
        if (key == "" || key == null) {
            return defaultValue;
        }
        String result = (String) map.get(key);
        if (result == null) {
            return defaultValue;
        } else {
            return result;
        }
    }

    public static int getIntFromMap(Map<String, Object> map, String key) {
        if (key == "" || key == null) {
            return 0;
        }
        if (map.get(key) == null) {
            return 0;
        }
        return Integer.parseInt((String) map.get(key));
    }

    
    public static String json2xml(String jsonString) {
        XMLSerializer xmlSerializer = new XMLSerializer();
        return xmlSerializer.write(JSONSerializer.toJSON(jsonString));
        // return xmlSerializer.write(JSONArray.fromObject(jsonString));//这种方式只支持JSON数组
    }
}

