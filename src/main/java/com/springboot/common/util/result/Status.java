package com.springboot.common.util.result;

/**
 * 结果状态-枚举类
 * 
 * @author YMC
 *
 */
public enum Status {
	
	// 成功、失败、错误 
	SUCCESS("1", "200"), FAILED("0", "501"), ERROR("-1", "500");
	
	// 响应业务状态
	private String status;
	
	// 响应消息
	private String message;
	
	Status(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}