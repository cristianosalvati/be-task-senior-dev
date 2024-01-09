package com.amaris.task.common.util;

import java.util.Collections;
import java.util.HashSet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.amaris.task.data.domain.dto.ErrorDto;
import com.amaris.task.data.domain.dto.ResponseDto;
import com.amaris.task.data.domain.enumeratiom.ResponseDtoStatus;

/**
 * @Author Cristiano
 * 
 * An util class for manage objects in response
 */
public class ResponseUtil {

	static public HashSet<StackTraceElement> createCauseSetList(Throwable cause) {
		HashSet<StackTraceElement> causeSetList = new HashSet<StackTraceElement> ();
		
		while (cause != null) {
			Collections.addAll(causeSetList, cause.getStackTrace());
			cause = cause.getCause();
		}
		return causeSetList;
	}
	
	static public ResponseEntity<ResponseDto<?>> createErrorResponse(Exception ex, HttpStatus status) {
		ResponseDto<ErrorDto> response = new ResponseDto<ErrorDto>();
		ErrorDto errorDto = new ErrorDto();
		errorDto.setDescription(ex.getMessage());
		errorDto.setStackTrace(ResponseUtil.createCauseSetList(ex));
		response.addResponseData(errorDto);
		response.setStatus(ResponseDtoStatus.ERROR);
		return ResponseEntity.status(status).body(response);
	}
}
