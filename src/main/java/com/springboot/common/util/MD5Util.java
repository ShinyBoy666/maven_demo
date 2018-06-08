package com.springboot.common.util;

import java.security.MessageDigest;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Util {
	
	private static final String SALT = "mrbird";

	private static final String ALGORITH_NAME = "md5";

	private static final int HASH_ITERATIONS = 2;

	public static String md5(String value) throws Exception {
		StringBuffer buffer = new StringBuffer();
		MessageDigest digest = MessageDigest.getInstance("MD5");
		
		char[] values = value.toCharArray();
		byte[] bytes = new byte[values.length];
		
		for(int i = 0; i < values.length; i++) {
			bytes[i] = (byte) values[i];
		}
		byte[] md5 = digest.digest(bytes);
		for(int i = 0; i < md5.length; i++) {
			int val = ((int) md5[i]) & 0xff;
			if(val < 16) {
				buffer.append("0");
			}
			buffer.append(Integer.toHexString(val));
		}
		return buffer.toString();
	}
	
	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString;
	}
	
	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	public static String encrypt(String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
		return newPassword;
	}

	public static String encrypt(String username, String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(username.toLowerCase() + SALT),
				HASH_ITERATIONS).toHex();
		return newPassword;
	}
	public static void main(String[] args) {
		
		System.out.println(MD5Util.encrypt("tester", "123456"));
	}
	
}