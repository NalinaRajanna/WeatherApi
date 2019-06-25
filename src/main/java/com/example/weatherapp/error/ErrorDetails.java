package com.example.weatherapp.error;

import java.io.Serializable;
import java.util.List;


public class ErrorDetails implements Serializable{
	
	   
	Error error;

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}
	
	

}
