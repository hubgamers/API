package com.hubgamers.api.response;

public class ResponseJson<T> {
	private T data;
	private String message;
	private int statusCode;
	
	private long exp;
	
	public ResponseJson(T data, int statusCode) {
		this.data = data;
		this.statusCode = statusCode;
	}
	
	public ResponseJson(T data, int statusCode, long exp) {
		this.data = data;
		this.statusCode = statusCode;
		this.exp = exp;
	}
	
	public ResponseJson(String message, int statusCode) {
		this.message = message;
		this.statusCode = statusCode;
	}
	
	public T getData() {
		return data;
	}
	
	public String getMessage() {
		return message;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public long getExp() {
		return exp;
	}
}
