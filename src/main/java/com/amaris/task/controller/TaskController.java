package com.amaris.task.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amaris.task.common.exception.ModelProcessingException;
import com.amaris.task.common.exception.ServiceException;
import com.amaris.task.common.util.ResponseUtil;
import com.amaris.task.data.domain.dto.EmployeeTaskDto;
import com.amaris.task.data.domain.dto.ResponseDto;
import com.amaris.task.data.domain.dto.SearchFilterDto;
import com.amaris.task.data.domain.dto.TaskDto;
import com.amaris.task.data.domain.enumeratiom.ResponseDtoStatus;
import com.amaris.task.service.TaskService;

/** @author salvati.cristiano_am
 * 
 * a rest controller defining task's operations.  
 */

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
    private TaskService taskService;
    
    @PutMapping("/create")
    public  ResponseEntity<ResponseDto<?>> createNewTask(
    			@RequestBody(required=true) TaskDto dto){
    	try {
    		ResponseDto<TaskDto> response = new ResponseDto<TaskDto>();
    		response.setStatus(ResponseDtoStatus.SUCCESS);
    		response.addResponseData(taskService.createTask(dto));
    		return ResponseEntity.ok(response);
		
		}catch(ModelProcessingException e) {
			HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
			return ResponseUtil.createErrorResponse(e, status);
		}catch(Exception e) {
			HttpStatus status = HttpStatus.BAD_REQUEST;
			return ResponseUtil.createErrorResponse(e, status);
		}
    }
    
    @DeleteMapping("/delete/{taskId}")
    public  ResponseEntity<ResponseDto<?>> deleteTask(
    			@PathVariable Long taskId){
    	try {
    		ResponseDto<TaskDto> response = new ResponseDto<TaskDto>();
    		response.setStatus(ResponseDtoStatus.SUCCESS);
    		response.addResponseData(taskService.deleteTask(taskId));
    		return ResponseEntity.ok(response);
    		
		}catch(ServiceException e) {
			HttpStatus status = HttpStatus.BAD_REQUEST;
			return ResponseUtil.createErrorResponse(e, status);
		}catch(Exception e) {
			HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
			return ResponseUtil.createErrorResponse(e, status);
		}	
    }
    
    @PostMapping("/find-by")
    public ResponseEntity<ResponseDto<?>> findTaskByFilter(
    			@RequestBody(required=true) SearchFilterDto dto){
    	try {
    		ResponseDto<EmployeeTaskDto> response = new ResponseDto<EmployeeTaskDto>();
    		response.setStatus(ResponseDtoStatus.SUCCESS);
    		response.addResponseData(taskService.findTasksBySearchFilter(dto));
    		return ResponseEntity.ok(response);
    		
    	}catch(ModelProcessingException e) {
			HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
			return ResponseUtil.createErrorResponse(e, status);
		}catch(Exception e) {
			HttpStatus status = HttpStatus.BAD_REQUEST;
			return ResponseUtil.createErrorResponse(e, status);
		}
    }
    
    // Endpoint per ri-assegnare un'attività a un impiegato
    @PostMapping("/assign/{taskId}/to/{employeeId}")
    public ResponseEntity<ResponseDto<?>> assignTaskToEmployee(
            @PathVariable Long taskId,
            @PathVariable Long employeeId) {
    	try {
    		ResponseDto<TaskDto> response = new ResponseDto<TaskDto>();
    		response.setStatus(ResponseDtoStatus.SUCCESS);
    		response.addResponseData(taskService.assignTaskToEmployee(taskId, employeeId));
    		
    		return ResponseEntity.ok(response);
    	}catch(Exception e) {
			HttpStatus status = HttpStatus.BAD_REQUEST;
			return ResponseUtil.createErrorResponse(e, status);
		}
    }
    
 // Endpoint per de-assegnare un'attività da un impiegato
    @PostMapping("/unassign/{taskId}/from/{employeeId}")
    public ResponseEntity<ResponseDto<?>> unassignTaskFromEmployee(
            @PathVariable Long employeeId,
            @PathVariable Long taskId) {
    	try {
    		ResponseDto<TaskDto> response = new ResponseDto<TaskDto>();
    		response.setStatus(ResponseDtoStatus.SUCCESS);
    		response.addResponseData(taskService.unassignTaskFromEmployee(taskId, employeeId));
    		
    		return ResponseEntity.ok(response);
    	}catch(Exception e) {
			HttpStatus status = HttpStatus.BAD_REQUEST;
			return ResponseUtil.createErrorResponse(e, status);
		}
    }
    
    // Endpoint per modificare la data di scadenza di un'attività
    @PostMapping("/due-date/{taskId}")
    public ResponseEntity<ResponseDto<?>> updateTaskDueDate(
            @PathVariable Long taskId,
            @RequestParam Date newDueDate) {
    	try {
    		ResponseDto<TaskDto> response = new ResponseDto<TaskDto>();
    		response.setStatus(ResponseDtoStatus.SUCCESS);
    		response.addResponseData(taskService.updateTaskDueDate(taskId, newDueDate));
	        
	        return ResponseEntity.ok(response);
    	}catch(Exception e) {
			HttpStatus status = HttpStatus.BAD_REQUEST;
			return ResponseUtil.createErrorResponse(e, status);
		}
    }
    
}
