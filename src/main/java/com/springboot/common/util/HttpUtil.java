package com.springboot.common.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.alibaba.fastjson.JSONException;

public class HttpUtil {

	/**
	 * 向指定 URL 发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			URLConnection connection = realUrl.openConnection();
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("contentType", "utf-8");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new PrintWriter(conn.getOutputStream());
			out.print(param);
			out.flush();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 向指定 URL 发送PUT方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendHttpPut(String path, String json, String Authorization, String timestamp, String nonce,
			String signature) throws IOException, JSONException {
		// 创建连接
		URL url = new URL(path);
		HttpURLConnection connection;
		StringBuffer sbuffer = null;
		try {
			// 添加 请求内容
			connection = (HttpURLConnection) url.openConnection();
			// 设置http连接属性
			connection.setDoOutput(true);// http正文内，因此需要设为true, 默认情况下是false;
			connection.setDoInput(true);// 设置是否从httpUrlConnection读入，默认情况下是true;
			connection.setRequestMethod("PUT"); // 可以根据需要 提交
												// GET、POST、DELETE、PUT等http提供的功能
			// connection.setUseCaches(false);//设置缓存，注意设置请求方法为post不能用缓存
			//connection.setRequestProperty("Host", "*******");  设置请求的服务器网址，域名，例如***.**.***.***
			connection.setRequestProperty("Accept-Charset", "utf-8"); // 设置编码语言
			connection.setRequestProperty("Authorization", "bearer " + Authorization);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("timestamp", timestamp);
			connection.setRequestProperty("nonce", nonce);
			connection.setRequestProperty("signature", signature);

			connection.setReadTimeout(10000);// 设置读取超时时间
			connection.setConnectTimeout(10000);// 设置连接超时时间
			connection.connect();
			OutputStream out = connection.getOutputStream();// 向对象输出流写出数据，这些数据将存到内存缓冲区中
			out.write(json.toString().getBytes()); // out.write(new
													// String("测试数据").getBytes());
													// //刷新对象输出流，将任何字节都写入潜在的流中
			out.flush();
			// 关闭流对象,此时，不能再向对象输出流写入任何数据，先前写入的数据存在于内存缓冲区中
			out.close();
			// 读取响应
			if (connection.getResponseCode() == 200) {
				// 从服务器获得一个输入流
				InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());// 调用HttpURLConnection连接对象的getInputStream()函数,
																									// 将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端。
				BufferedReader reader = new BufferedReader(inputStream);

				String lines;
				sbuffer = new StringBuffer("");

				while ((lines = reader.readLine()) != null) {
					lines = new String(lines.getBytes(), "utf-8");
					sbuffer.append(lines);
				}
				reader.close();
			} else {
				System.out.println("请求失败" + connection.getResponseCode());
			}
			// 断开连接
			connection.disconnect();
		} catch (IOException e) {
			System.out.println("发送 PUT 请求出现异常！" + e);
			e.printStackTrace();
		}
		 return new String(sbuffer.toString());
	}

	@SuppressWarnings("deprecation")
	public static String sendSSLPost(String url, String param) {
		String result = "";
		String urlNameString = url + "?" + param;
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
			URL console = new URL(urlNameString);
			HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.connect();
			InputStream is = conn.getInputStream();
			DataInputStream indata = new DataInputStream(is);
			String ret = "";
			while (ret != null) {
				ret = indata.readLine();
				if (ret != null && !ret.trim().equals("")) {
					result += new String(ret.getBytes("ISO-8859-1"), "utf-8");
				}
			}
			conn.disconnect();
			indata.close();
		} catch (Exception e) {
			System.out.println("发送SSL POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		return result;
	}

	private static class TrustAnyTrustManager implements X509TrustManager {
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

}