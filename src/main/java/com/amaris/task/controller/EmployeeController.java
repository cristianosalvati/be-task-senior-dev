package com.amaris.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amaris.task.common.exception.ModelProcessingException;
import com.amaris.task.common.exception.ServiceException;
import com.amaris.task.common.util.ResponseUtil;
import com.amaris.task.data.domain.dto.EmployeeDto;
import com.amaris.task.data.domain.dto.ResponseDto;
import com.amaris.task.data.domain.dto.TaskDto;
import com.amaris.task.data.domain.enumeratiom.ResponseDtoStatus;
import com.amaris.task.service.EmployeeService;

/** @author salvati.cristiano_am
 * 
 * a rest controller defining employee's operations.  
 */

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
    private EmployeeService employeeService;

	@PutMapping("/create")
    public  ResponseEntity<ResponseDto<?>> createNewEmployee(
    			@RequestBody(required=true) EmployeeDto dto){
    	try {
    		ResponseDto<TaskDto> response = new ResponseDto<TaskDto>();
    		response.setStatus(ResponseDtoStatus.SUCCESS);
    		response.addResponseData(employeeService.createEmployee(dto));
    		return ResponseEntity.ok(response);
		
		}catch(ModelProcessingException e) {
			HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
			return ResponseUtil.createErrorResponse(e, status);
		}catch(Exception e) {
			HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
			return ResponseUtil.createErrorResponse(e, status);
		}
 	}
	
	@DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<ResponseDto<?>> deleteTask(
    			@PathVariable Long employeeId){
		try {
    		ResponseDto<TaskDto> response = new ResponseDto<TaskDto>();
    		response.setStatus(ResponseDtoStatus.SUCCESS);
    		response.addResponseData(employeeService.deleteEmployee(employeeId));
    		return ResponseEntity.ok(response);
		}catch(ModelProcessingException e) {
			HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
			return ResponseUtil.createErrorResponse(e, status);
		}catch(ServiceException e) {
			HttpStatus status = HttpStatus.BAD_REQUEST;
			return ResponseUtil.createErrorResponse(e, status);
		}catch(Exception e) {
			HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
			return ResponseUtil.createErrorResponse(e, status);
		}	
	}
	
	@GetMapping("/all")
	public ResponseEntity<ResponseDto<?>> getAllEmployee() {
		try {
			ResponseDto<EmployeeDto> response = new ResponseDto<EmployeeDto>();
			response.setStatus(ResponseDtoStatus.SUCCESS);
			response.setItems(employeeService.getAllEmployees());
			return ResponseEntity.ok(response);
		}catch(ModelProcessingException e) {
			HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
			return ResponseUtil.createErrorResponse(e, status);
		}catch(Exception e) {
			HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
			return ResponseUtil.createErrorResponse(e, status);
		}
	}
}
