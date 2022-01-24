package com.groceryproduct.productms.response.entity;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class ErrorInfo implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private int stausCode;
	private String message;
	private String uri;
	
	
	
	public ErrorInfo() {
		super();
	}

	public ErrorInfo(HttpStatus status, int stausCode, String message, String uri) {
		super();
		this.status = status;
		this.stausCode = stausCode;
		this.message = message;
		this.uri = uri;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public int getStausCode() {
		return stausCode;
	}

	public void setStausCode(int stausCode) {
		this.stausCode = stausCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
