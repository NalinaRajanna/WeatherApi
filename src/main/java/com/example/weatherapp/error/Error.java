package com.example.weatherapp.error;

import java.io.Serializable;

public class Error implements Serializable{
	
	
	String code;
	String message;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
