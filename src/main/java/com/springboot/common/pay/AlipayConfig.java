package com.springboot.common.pay;

/**
 * 支付宝支付各种配置数据
 * 
 * @author ymc
 *
 * @date 2018:5:12
 *
 */
public class AlipayConfig {

	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088611713593781";
	// 收款支付宝账号，一般情况下收款账号就是签约账号
	public static String seller_email = "jipin20140814@126.com";
	// 商户的私钥
	public static String key = "7my96x8qmngr6mn3wq9estc0p7gml4f8";
	// 商户的私钥 RSA 加密 转换成pkcs8格式
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANOwHj2H3puNd6L3DDjTkKieBopwCraDL7o5vQnfZURgl0Unb1c0Gr0zVjRmR3F/9PQVNEwJZ24yXJ4NWkj/umIng7TZbn7FjpO8t7dOvFdoa60LwVwNyCSHLf36FcjpxEvfB4kV2MvzB5SKZ/We36JbY6l0f+ND1wVbEXPyQHXDAgMBAAECgYAe80sYl/r2J1Lsos5IqLYn0SMHrqYCVV/vDG4VOXJm8vBrDf77cwTN0Omzs5iSgbDg1xDUZTOYKwjbmvwuXCV13FfBaEuQn21ExTnBLQ1XQKK3oBc4wjhzP+w72utUItILdZeRQLD7lCZQJ3DU5JDwpTK8QAEWsThV+cEkwwLvMQJBAOzz1l3h2Q7ctrlGX/rHCSxMEq+7YbHaseIP7FziofmQhIb4Qf1owOedAm48hKh93MiKiDEYsUuDatlV6Xvzq00CQQDktF7veWMAivVdrYzLGeZA/JjXfE9+XB9wZHJZ8uWVybSm5m6Tb3K6kwMFin2WvYnHH2jl29JsMkhS12tcEn1PAkEAobWPWKbncYMjmLH0krr/AaIxJoklFTIBMBxZ3NI2ZJFCQLJwpra039yBaiYXCr5KzPnuFEOby8M2SnidTaBwCQJAaHJfcOp05kiP6ipaRF8dosw5WR/yhfi4evHEr4OYiiHGGPrfwQGC2eZN2Pf4hyEkjQWxOFh97t+/2+V5qGDhTQJBAJZbDxlekxFJTKvVqlf9ILXxNSS06be6KHd+IqPTm37isf1h6veluxo75UsOPuPuDz/tYgVa8MWVQtz7/L35Tf4=";
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
}