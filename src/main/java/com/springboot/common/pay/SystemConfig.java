package com.springboot.common.pay;

public class SystemConfig {
	
	/** 字符编码   */
	public static String input_charset = "utf-8";
	
	/** 应用域名   */
	public static String path = "http://wap.51shunkai.com";
	
	/** 支付宝异步通知*/
	public final static String ali_notify = "/pay/alipay/notify/";
	
	/** 支付宝同步通知 即时到账  */
	public final static String ali_return = "/pay/alipay/return/";
	
	/** 微信异步通知 app支付  */
	public final static String wx_notify = "/pay/wxpay/notify/";
	
	/** wap 支付宝  单位 元*/
	public final static String ali_wap = "http://wap.51shunkai.com/pages/{2}/alipay.html?r={0}&m={1}";
			
	/** wap 微信   单位 分*/
	public final static String wx_wap = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx337f6519a4a6c87f&redirect_uri=http%3a%2f%2fpay.51shunkai.com%2fWxPay%2fSetWxPayParams&response_type=code&scope=snsapi_base&state={0}|{1}|{2}#wechat_redirect";
}
