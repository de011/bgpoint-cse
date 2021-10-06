package com.dromedian.comunico.bgpoint.cse.globalexception;
public class DataNotFoundException extends RuntimeException{
	public DataNotFoundException(){}
	public DataNotFoundException(String message){
		super(message);
	}
}
