package com.news.news.entity.news;

public class News {
	private String reason;
	private New result;
	private int error_code;
	public News(String reason, New result, int error_code) {
		super();
		this.reason = reason;
		this.result = result;
		this.error_code = error_code;
	}
	public News() {
		super();
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public New getResult() {
		return result;
	}
	public void setResult(New result) {
		this.result = result;
	}
	public int getError_code() {
		return error_code;
	}
	public void setError_code(int error_code) {
		this.error_code = error_code;
	}
	

}
