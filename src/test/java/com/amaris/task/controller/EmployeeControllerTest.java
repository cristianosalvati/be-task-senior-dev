package com.amaris.task.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.amaris.task.common.exception.ModelProcessingException;
import com.amaris.task.common.exception.ServiceException;
import com.amaris.task.common.util.ResponseUtil;
import com.amaris.task.data.domain.dto.EmployeeDto;
import com.amaris.task.data.domain.dto.ResponseDto;
import com.amaris.task.data.domain.enumeratiom.ResponseDtoStatus;
import com.amaris.task.service.EmployeeService;

public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateNewEmployee(){
    	try {
	        // Create a mock EmployeeDto
	        EmployeeDto employeeDto = new EmployeeDto();
	        employeeDto.setId(1L);
	        employeeDto.setFirstName("John");
	        employeeDto.setLastName("Doe");
	
	        // Mock the behavior of the employeeService.createEmployee method
	        when(employeeService.createEmployee(employeeDto)).thenReturn(employeeDto);
	
	        // Test the createNewEmployee method
	        ResponseEntity<ResponseDto<?>> responseEntity = employeeController.createNewEmployee(employeeDto);
	
	        // Verify that the response entity status is OK
	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	
	        // Verify that the response entity contains a success status
	        ResponseDto<?> responseDto = responseEntity.getBody();
	        assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus());
	
	        // Verify that the response entity contains the expected EmployeeDto
	        assertEquals(1L, ((EmployeeDto) responseDto.getItems().get(0)).getId());
	        assertEquals("John", ((EmployeeDto) responseDto.getItems().get(0)).getFirstName());
	        assertEquals("Doe", ((EmployeeDto) responseDto.getItems().get(0)).getLastName());
      	}catch(Throwable t) {
    		fail(t.getMessage());
    	}   
   	}

    // Test the deleteTask method
    @Test
    public void testDeleteTask() {
    	try {
	        // Mock the behavior of the employeeService.deleteEmployee method
	        when(employeeService.deleteEmployee(1L)).thenReturn(new EmployeeDto());
	
	        // Test the deleteTask method
	        ResponseEntity<ResponseDto<?>> responseEntity = employeeController.deleteTask(1L);
	
	        // Verify that the response entity status is OK
	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	
	        // Verify that the response entity contains a success status
	        ResponseDto<?> responseDto = responseEntity.getBody();
	        assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus());
      	}catch(Throwable t) {
    		fail(t.getMessage());
    	}
    }

    // Test the deleteTask method when ServiceException is thrown
    @Test
    public void testDeleteTaskServiceException() {
    	try {
	        // Mock the behavior of the employeeService.deleteEmployee method to throw a ServiceException
	        when(employeeService.deleteEmployee(1L)).thenThrow(new ServiceException("Service exception"));
	
	        // Test the deleteTask method
	        ResponseEntity<ResponseDto<?>> responseEntity = employeeController.deleteTask(1L);
	
	        // Verify that the response entity status is BAD_REQUEST
	        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	
	        // Verify that the response entity contains an error status
	        ResponseDto<?> responseDto = responseEntity.getBody();
	        assertEquals(ResponseDtoStatus.ERROR, responseDto.getStatus());
      	}catch(Throwable t) {
    		fail(t.getMessage());
    	}
    }

    // Test the getAllEmployee method
    @Test
    public void testGetAllEmployee(){
    	try {
	        // Create a mock list of EmployeeDto objects
	        EmployeeDto employeeDto1 = new EmployeeDto();
	        employeeDto1.setId(1L);
	        employeeDto1.setFirstName("John");
	        employeeDto1.setLastName("Doe");
	        EmployeeDto employeeDto2 = new EmployeeDto();
	        employeeDto2.setId(2L);
	        employeeDto2.setFirstName("Jane");
	        employeeDto2.setLastName("Smith");
	
	        // Mock the behavior of the employeeService.getAllEmployees method
	        when(employeeService.getAllEmployees()).thenReturn(List.of(employeeDto1, employeeDto2));
	
	        // Test the getAllEmployee method
	        ResponseEntity<ResponseDto<?>> responseEntity = employeeController.getAllEmployee();
	
	        // Verify that the response entity status is OK
	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	
	        // Verify that the response entity contains a success status
	        ResponseDto<?> responseDto = responseEntity.getBody();
	        assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus());
	
	        // Verify that the response entity contains the expected EmployeeDto objects
	        assertEquals(2, responseDto.getItems().size());
	        assertEquals(1L, ((EmployeeDto) responseDto.getItems().get(0)).getId());
	        assertEquals("John", ((EmployeeDto) responseDto.getItems().get(0)).getFirstName());
	        assertEquals("Doe", ((EmployeeDto) responseDto.getItems().get(0)).getLastName());
	        assertEquals(2L, ((EmployeeDto) responseDto.getItems().get(1)).getId());
	        assertEquals("Jane", ((EmployeeDto) responseDto.getItems().get(1)).getFirstName());
	        assertEquals("Smith", ((EmployeeDto) responseDto.getItems().get(1)).getLastName());
      	}catch(Throwable t) {
    		fail(t.getMessage());
    	}
    }
}

