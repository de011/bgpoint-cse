package com.dromedian.comunico.bgpoint.cse.dto;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ResponseModel {
	private boolean status;
	private String message;
	private LocalDateTime timeStamp;


	@JsonInclude(Include.NON_NULL)
	private Object data;

	public ResponseModel(){}


	public ResponseModel(boolean status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}



	public ResponseModel(boolean status, String message, LocalDateTime timeStamp, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
		this.data = data;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}


	@Override
	public String toString() {
		return "ResponseModel [status=" + status + ", message=" + message + ", data=" + data + "]";
	}


}