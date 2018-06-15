package com.springboot.common.util.result;

/**
 * @Description:200：表示成功
 * 				500：表示错误，错误信息在msg字段中
 * 				501：bean验证错误，不管多少个错误都以map形式返回
 * 				502：拦截器拦截到用户token出错
 * 				555：异常抛出信息
 * @author YMC
 * @date 2018年5月20日 
 * @version V1.0
 */
public class JsonUtils {

	// 响应业务状态
    private String status;

    // 响应消息
    private String message;

    // 响应中的数据
    private Object data;
    
    public JsonUtils(Status status) {
		this.status = status.getStatus();
		this.message = status.getMessage();
	}
	
	public JsonUtils(String status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public JsonUtils(String status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public boolean isSucceed() {
		if(this.status.equals(Status.SUCCESS.getStatus())) {
			return true;
		}
		return false;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		if(this.status.equals(Status.ERROR.getStatus())) {
			this.data = null;
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getdata() {
		return data;
	}

	public void setdata(Object data) {
		this.data = data;
	}
	
}