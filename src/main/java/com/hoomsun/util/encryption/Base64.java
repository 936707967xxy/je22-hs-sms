package com.hoomsun.util.encryption;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import com.hoomsun.util.Constants;
import com.hoomsun.util.message.LogCvt;
import com.hoomsun.util.message.ResBody;

public class Base64 {
	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}

	/**
	 * <p>
	 * Copyright: Copyright (c) 2018
	 * </p>
	 * <p>
	 * Company: www.hoomsun.com
	 * </p>
	 * 
	 * @author xinyuan.xu@hoomsun.com
	 * @param md5Key
	 * @param context
	 * @return
	 * @Description:根据MD5key和明文报文加密
	 * @param 2018年7月16日
	 */
	public static String encryptObject(String md5Key, String context) {
		StringBuffer sb = new StringBuffer();
		try {
			sb.append(md5Key);
			//String mtext = java.net.URLEncoder.encode(context, "GBK");
			String mtext = UtilsBase64.encrypt(context, md5Key);
			//LogCvt.info("加密结果："+mtext);
			sb.append(mtext);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogCvt.error("报文信息加密异常.....");
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * <p>
	 * Copyright: Copyright (c) 2018
	 * </p>
	 * <p>
	 * Company: www.hoomsun.com
	 * </p>
	 * 
	 * @author xinyuan.xu@hoomsun.com
	 * @param md5Key
	 * @param pwdContext
	 * @return
	 * @Description:根据MD5key和密文解密字符串
	 * @param 2018年7月16日
	 */
	public static ResBody decryptObject(String md5Key, String pwdContext)
			throws Exception,IllegalArgumentException {
		ResBody res = new ResBody();
		String decryResult = "";
		String md5 = Md5.getResult(Constants.SECRET_TEMP_KEY).trim();
		if (!md5Key.equals(md5)) {
			res.setCode(ResBody.ERROR_CODE);
			res.setMessage("非法公共密钥参数，不能继续执行......");
			LogCvt.error("非法公共密钥参数，不能继续执行......");
			return res;
		}
		if (pwdContext.indexOf(md5) != 0) {
			LogCvt.error("报文头信息被篡改，不能继续执行......");
			res.setCode(ResBody.ERROR_CODE);
			res.setMessage("报文头信息被篡改，不能继续执行......");
			return res;
		}
		//decryResult = UtilsBase64.decrypt(pwdContext.replace(md5Key, ""), md5Key);
		res.setCode(ResBody.SUCCESS_CODE);
		res.setData(decryResult);
		return res;
	}
}
