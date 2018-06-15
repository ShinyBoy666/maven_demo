package com.springboot.common.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.springboot.common.util.DateUtil;

/**
 * 基础-控制类
 * 
 * @author 
 *
 */
public class BaseController {
	
	private static final String[] HEADERS_TO_TRY = {
		"X-Forwarded-For",
		"Proxy-Client-IP",
		"WL-Proxy-Client-IP",
		"HTTP_X_FORWARDED_FOR",
		"HTTP_X_FORWARDED",
		"HTTP_X_CLUSTER_CLIENT_IP",
		"HTTP_CLIENT_IP",
		"HTTP_FORWARDED_FOR",
		"HTTP_FORWARDED",
		"HTTP_VIA",
		"REMOTE_ADDR",
		"X-Real-IP"
	};
	
	/**
	 * 格式化日期格式
	 * 
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(ServletRequestDataBinder binder) {
		DateFormat df = new SimpleDateFormat(DateUtil.DEFAULT_DATE_FORMAT);
		CustomDateEditor editor = new CustomDateEditor(df, true);
		binder.registerCustomEditor(Date.class, editor);
	}
	
	/**
	 * 获取客户端地址
	 * 
	 * @param request
	 * 			请求对象
	 * @return
	 */
	public String ip(HttpServletRequest request) {
		for (String header : HEADERS_TO_TRY) {
			String ip = request.getHeader(header);
			if(StringUtils.isNotBlank(ip)) {
				return ip;
			}
		}
		String ip = request.getRemoteAddr();
		if(ip.equals("0:0:0:0:0:0:0:1")) {
			ip = "127.0.0.1";
		}
		return ip;
	}

}