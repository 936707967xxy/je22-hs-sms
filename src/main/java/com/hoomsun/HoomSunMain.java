package com.hoomsun;

import com.hoomsun.util.message.LogCvt;

public class HoomSunMain {
	/*
	 *2018年3月12日下午1:40:25
	 *xinyuan.xu@hoomsun.com
	 */
	
	public static void main(String[] args) {
//		if(System.getProperty("dir")==null){
//			System.setProperty("dir", System.getProperty("user.dir"));
//		}
		LogCvt.info(System.getProperty("catalina.home"));
	}
}
