package com.springboot.common.util;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Formatter;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * 字符---工具类
 * 
 * @author ymc
 *
 * @date 2018:5:12
 */
public class StringsUtil {

	// static修饰的String，会在堆内存中复制一份常量池中的值。
	// 所以调用 static final String 变量，
	// 实际上是直接调用堆内存的地址，不会遍历字符串池中的对象，节省了遍历时间。

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * 
	 * @return true:字符串为空，false:字符串非空
	 */
	public static boolean isEmpty(String str) {
		return ((str == null) || (str.trim().length() == 0) || "null".equals(str) || "".equals(str));
	}

	/**
	 * 字符串截取
	 * 
	 * @param value
	 * @param size
	 *            截取长度
	 * @return
	 */
	public static String substring(String value, int size) {
		if (isEmpty(value)) {
			return "";
		}
		if (value.length() <= size) {
			return value;
		}
		return value.substring(0, size);
	}

	/**
	 * 验证对象是否为 enmty
	 * 
	 * @param obj
	 * @return
	 */
	public static <T> boolean isEmpty(T t) {
		if (null == t) {
			return true;
		}

		// t extends Integer
		if (t instanceof Number && ((Number) t).doubleValue() == 0) {
			return true;
		}

		// t extends CharSequence
		if (t instanceof CharSequence && "".equals(t.toString().trim())) {
			return true;
		}

		// t extends Collection
		if (t instanceof Collection && ((Collection<?>) t).isEmpty()) {
			return true;
		}
		// Map
		if (t instanceof Map && ((Map<?, ?>) t).isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 校验数据 是否全部为空 </br>
	 * 描述：只要有一个数据为空 => false，否则 => true
	 * 
	 * @param data
	 * @return
	 */
	public static boolean allEmpty(Map<String, Object> data) {
		if (isEmpty(data)) {
			return true;
		}
		for (Map.Entry<String, Object> entry : data.entrySet()) {
			if (isEmpty(entry) == true) {
				// 只要有一个不为 Empty ,则表示不全为 空
				return false;
			}
		}
		return true;
	}

	/**
	 * 校验数据 是否全部为空 </br>
	 * 描述：只要有一个数据为空 => false，否则 => true
	 * 
	 * @param data
	 * @return
	 */
	public static boolean allEmpty(Object... list) {
		if (null == list || list.length <= 0) {
			return true;
		}
		for (Object item : list) {
			if (isEmpty(item) == true) {
				// 只要有一个不为 Empty ,则表示不全为 空
				return false;
			}
		}
		return true;
	}

	/**
	 * 首字母大写
	 * 
	 * @param target
	 *            目标字符
	 * @return String
	 */
	public static String toUpperCase(String target) {
		if (!Character.isUpperCase(target.charAt(0))) {
			StringBuilder builder = new StringBuilder(target);
			builder.setCharAt(0, Character.toUpperCase(target.charAt(0)));
			target = builder.toString();
		}
		return target;
	}

	/**
	 * 首字母小写
	 * 
	 * @param target
	 *            目标字符
	 * @return String
	 */
	public static String toLowerCase(String target) {
		if (!Character.isLowerCase(target.charAt(0))) {
			StringBuilder builder = new StringBuilder(target);
			builder.setCharAt(0, Character.toLowerCase(target.charAt(0)));
			target = builder.toString();
		}
		return target;
	}

	/**
	 * byte转16进制
	 * 
	 * @param param
	 * 
	 * @return String
	 */
	public static String byteToHex(final byte[] param) {
		Formatter formatter = new Formatter();
		for (byte b : param) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	/**
	 * 获取IP地址
	 * 
	 * @param request
	 * 
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip != null && ip.contains(",")) {
			ip = ip.split(",")[0];
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获取唯一标识---UUID
	 * 
	 * @return String
	 */
	public static String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 生成邀请码
	 * 
	 * @return
	 */
	public static String inviteCode(int n) {
		final char[] keleyi = "123456789ABCDEFGHIJKLMNPQRSTUVWXYZ".toCharArray();
		String UUID = getUuid();
		char[] ba = UUID.toCharArray();
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < n; i++) {
			sb.append(keleyi[((ba[i] + ba[n + i]) % 33)]);
		}
		return sb.toString();
	}

	/**
	 * 生成随机数
	 * 
	 * @param length
	 *            位数 ，默认4位
	 * @return
	 */
	public static String random(int length) {
		if (length == 0) {
			length = 4;
		}
		Number max = Math.pow(10, length);
		int random = new Random(System.nanoTime()).nextInt(max.intValue());
		return String.format(MessageFormat.format("%0{0}d", length), random);
	}

	/**
	 * 创建一个 2010年之后的时间戳
	 * 
	 * @return
	 */
	public static String createTimestamp() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2010, 1, 1, 0, 0, 0);
		long time_2010 = calendar.getTime().getTime();

		long time_now = new Date().getTime();

		long time_second = (time_now - time_2010) / 1000;

		return String.valueOf(time_second);
	}

	/**
	 * 生成单号
	 * 
	 * @param value
	 *            客户编号
	 * @return
	 */
	public static String createNumber(int value) {
		if (StringsUtil.isEmpty(value)) {
			return createTimestamp() + random(5);
		} else {
			return createTimestamp() + String.format("%05d", value);
		}
	}

	/**
	 * 生成主订单（一秒只能生成一个）
	 * 
	 * @param value
	 *            客户编号
	 * @return
	 */
	public static String createOrderNumber(String prefix, int value) {
		if (StringsUtil.isEmpty(value)) {
			return prefix + createTimestamp() + random(5);
		} else {
			return prefix + createTimestamp() + String.format("%05d", value);
		}
	}

}