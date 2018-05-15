package com.hoomsun.util.other;

public enum ResultCode {
	success("0000","操作成功"),
	failed("9999","操作失败"), //描述的错误信息自定义
	//登录相关
	nologin("9900","未登录或会话超时，请重新登录"),
	nouser("9000","无此用户"),
	errortimesout("9100","已锁定，请30分钟后再试或重置密码后登录"),
	firstlogin("9200","首次登录,请先修改密码"),
	nopermission("9300","无请求权限"),
	badRequest("4000", "无法处理的请求"),
	resetSelf("9400","当前登录用户已重置，请重新登录"),
	userOnline("9500","当前用户已登录，是否强制登录？"),
	//下载系统账单相关
	kjpay("00","快捷支付"),
    jfpay("01","积分支付"),
    wxpay("02","微信支付"),
    alipay("03","支付宝支付"),
    //日账单查询导出渠道
    bill_wxpay("00","微信"),
    bill_alipay("01","支付宝"),
    bill_kjpay("03","安徽农金"),
    bill_jfpay("04","积分"),
    bill_failed("0","异常"),
    bill_success("1","正常"),
    bill_waitSettStatus("0","未结算"),
    bill_settedStatus("1","已结算"),
    bill_settFStatus("2","结算失败"),
    bill_trandTypeClient("00","扫码支付"),
    bill_trandTypeServer("01","付款码支付"),
    bill_pay("1","支付"),
    bill_settlement("2","结算"),
    bill_refund("3","退款"),
    
    bill_billType_all("0","当日所有订单"),
    bill_billType_pay("1","当日成功支付的订单"),
    bill_billType_refund("2","当日退款的订单"),
    //对账是否对账
    bill_isDzYes("Y","是"),
    bill_isDzNo("N","否"),
    //第三方渠道结算
    sett_waitSettStatus("0","待结算"),
    sett_settedStatus("1","结算成功"),
    sett_partSettedStatus("2","部分结算"),
    //第三方结算商户明细结算
    /*商户结算状态*/
    sett_waitSettlement("0","待结算"),
    sett_haveSettlement("1","结算成功"),
    sett_falseSettlement("2","结算失败"),
    sett_connectSettlement("3","结算超时"),
    /*结算方式*/
    sett_systemType("1","系统结算"),
    sett_onesType("2","手动结算"),
	//第三方结算账单手动处理请求超时参数
	requestSettleInfo("9600","结算超时"),
    requestSettleSuccess("0000","结算成功"),
	//第三方行内渠道结算
    sett_SettTrue("1","已结算"),
    sett_SettFalse("2","结算失败"),
    
    //交易查询模块
    /*交易来源渠道*/
    trade_channelWechat("00","微信"),
    trade_channelAlipay("01","支付宝"),
    trade_channelBank("03","社区e银行"),
    trade_channnelAh("02","安徽农金")
	;
	private String code;
	
	private String msg;
	
	private ResultCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
}
