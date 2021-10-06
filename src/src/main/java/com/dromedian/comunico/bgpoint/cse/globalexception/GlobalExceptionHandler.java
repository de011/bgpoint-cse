package com.dromedian.comunico.bgpoint.cse.globalexception;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dromedian.comunico.bgpoint.cse.dto.ResponseModel;
import com.dromedian.comunico.bgpoint.cse.util.CSEConstants;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	ResponseEntity<ResponseModel> handelRuntimeException(RuntimeException re) {
		ResponseModel responseModel = new ResponseModel();
		responseModel.setStatus(Boolean.FALSE);
		responseModel.setMessage(CSEConstants.SYSTEM_EXCEPTION);
		responseModel.setData(null);
		return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(responseModel);

	}

	@ExceptionHandler(DataNotFoundException.class)
	ResponseEntity<ResponseModel> handelRuntimeException(DataNotFoundException datanotfound) {
		ResponseModel er = new ResponseModel();
		er.setStatus(false);
		er.setMessage(CSEConstants.RECORD_NOT_FOUND);
		er.setData(null);
		return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(er);
	}
}
