package com.hoomsun.util.encryption;

import java.math.BigInteger;
import java.security.MessageDigest;

import com.hoomsun.util.Constants;
import com.hoomsun.util.message.LogCvt;

public class Md5 {
	
	public static final String KEY_MD5 = "MD5";   

    public static  String  getResult(String inputStr){
        BigInteger bigInteger=null;
        try {
         MessageDigest md = MessageDigest.getInstance(KEY_MD5);   
         byte[] inputData = inputStr.getBytes(); 
         md.update(inputData);   
         bigInteger = new BigInteger(md.digest());   
        } catch (Exception e) {e.printStackTrace();}
        LogCvt.info("MD5密文:" + bigInteger.toString(16));
        return bigInteger.toString(16).toUpperCase();
    }
    
    public static void main(String[] args) {
		System.out.println(getResult(Constants.SECRET_TEMP_KEY));
	}
}
