package com.amaris.task.data.domain.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import com.amaris.task.common.exception.ModelProcessingException;
import com.amaris.task.data.domain.dto.EmployeeDto;
import com.amaris.task.data.orm.entity.Employee;

public class EmployeeDtoMapperTest {

    private EmployeeDtoMapper employeeDtoMapper;

    @BeforeEach
    public void setUp() throws ModelProcessingException {
 
        employeeDtoMapper = new EmployeeDtoMapper();
    }

    @Test
    public void testConvertToDto(){
    	try {
	        // Create a mock Employee entity
	        Employee employee = new Employee();
	        employee.setId(1L);
	        employee.setFirstName("John");
	        employee.setLastName("Doe");
	
	        // Create a mock EmployeeDto
	        EmployeeDto employeeDto = new EmployeeDto();
	        employeeDto.setId(1L);
	        employeeDto.setFirstName("John");
	        employeeDto.setLastName("Doe");
	
	        // Test the convertToDto method
	        EmployeeDto result = employeeDtoMapper.convertToDto(employee);
	
	        assertEquals(employeeDto.getId(), result.getId());
	        assertEquals(employeeDto.getFirstName(), result.getFirstName());
	        assertEquals(employeeDto.getLastName(), result.getLastName());
	        assertEquals(employeeDto.getRole(), result.getRole());
	        assertEquals(employeeDto.getEmail(), result.getEmail());
    	}catch(Throwable t) {
    		fail(t.getMessage());
    	}
    }

    @Test
    public void testConvertToEntity() {
        // Create a mock EmployeeDto
    	try {
	        EmployeeDto employeeDto = new EmployeeDto();
	        employeeDto.setId(1L);
	        employeeDto.setFirstName("John");
	        employeeDto.setLastName("Doe");
	
	        // Create a mock Employee entity
	        Employee employee = new Employee();
	        employee.setId(1L);
	        employee.setFirstName("John");
	        employee.setLastName("Doe");
	
	        // Test the convertToEntity method
	        Employee result = employeeDtoMapper.convertToEntity(employeeDto);
	
	        // Verify that the ModelMapper was used correctly and the result is as expected
	        assertEquals(employee.getId(), result.getId());
	        assertEquals(employee.getFirstName(), result.getFirstName());
	        assertEquals(employee.getLastName(), result.getLastName());
	        assertEquals(employee.getRole(), result.getRole());
	        assertEquals(employee.getEmail(), result.getEmail());
    	}catch(Throwable t) {
    		fail(t.getMessage());
    	}
    }
}