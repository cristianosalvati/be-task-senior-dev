package com.amaris.task.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.amaris.task.common.exception.ModelProcessingException;
import com.amaris.task.common.exception.ServiceException;
import com.amaris.task.data.domain.dto.EmployeeDto;
import com.amaris.task.data.domain.mapper.EmployeeDtoMapper;
import com.amaris.task.data.orm.entity.Employee;
import com.amaris.task.data.orm.repository.EmployeeRepository;

public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeDtoMapper employeeMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEmployee() {
    	try {
	        // Create a mock EmployeeDto
	        EmployeeDto employeeDto = new EmployeeDto();
	        employeeDto.setId(1L);
	        employeeDto.setFirstName("John");
	        employeeDto.setLastName("Doe");
	
	        // Create a mock Employee entity
	        Employee employee = new Employee();
	        employee.setId(1L);
	        employee.setFirstName("John");
	        employee.setLastName("Doe");
	
	        // Mock the behavior of the employeeMapper
	        when(employeeMapper.convertToEntity(employeeDto)).thenReturn(employee);
	
	        // Mock the behavior of the employeeRepository.save method
	        when(employeeRepository.save(employee)).thenReturn(employee);
	
	        // Test the createEmployee method
	        EmployeeDto result = employeeService.createEmployee(employeeDto);
	
	        // Verify that the save method was called with the correct entity
	        verify(employeeRepository).save(employee);
	
	        // Verify that the ID was set correctly in the EmployeeDto
	        assertEquals(1L, result.getId());
	        assertEquals("John", result.getFirstName());
	        assertEquals("Doe", result.getLastName());
    	}catch(Throwable t) {
    		fail(t.getMessage());
    	}
    }

    // Test the updateEmployee method
    @Test
    public void testUpdateEmployee() {
    	try {
	        // Create a mock EmployeeDto
	        EmployeeDto employeeDto = new EmployeeDto();
	        employeeDto.setId(1L);
	        employeeDto.setFirstName("John");
	        employeeDto.setLastName("Doe");
	
	        // Create a mock Employee entity
	        Employee employee = new Employee();
	        employee.setId(1L);
	        employee.setFirstName("John");
	        employee.setLastName("Doe");
	
	        // Mock the behavior of the employeeMapper
	        when(employeeMapper.convertToEntity(employeeDto)).thenReturn(employee);
	
	        // Test the updateEmployee method
	        employeeService.updateEmployee(employeeDto);
	
	        // Verify that the save method was called with the correct entity
	        verify(employeeRepository).save(employee);
    	}catch(Throwable t) {
    		fail(t.getMessage());
    	}
    }

    // Test the deleteEmployee method when employee is not found
    @Test
    public void testDeleteEmployeeNotFound() {
    	try {
	        // Mock the behavior of the employeeRepository.findById method
	        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());
	
	        // Test the deleteEmployee method with an employee that doesn't exist
	        assertThrows(ServiceException.class, () -> employeeService.deleteEmployee(1L));
	
	        // Verify that the deleteById method was not called
	        verify(employeeRepository, never()).deleteById(1L);
    	}catch(Throwable t) {
    		fail(t.getMessage());
    	}
    }

    // Test the getAllEmployees method
    @Test
    public void testGetAllEmployees() {
    	try {
	        // Create a mock list of Employee entities
	        List<Employee> employees = new ArrayList<>();
	        Employee employee1 = new Employee();
	        employee1.setId(1L);
	        employee1.setFirstName("John");
	        employee1.setLastName("Doe");
	        employees.add(employee1);
	        Employee employee2 = new Employee();
	        employee2.setId(2L);
	        employee2.setFirstName("Jane");
	        employee2.setLastName("Smith");
	        employees.add(employee2);
	
	        // Mock the behavior of the employeeRepository.findAll method
	        when(employeeRepository.findAll()).thenReturn(employees);
	
	        // Mock the behavior of the employeeMapper
	        EmployeeDto employeeDto1 = new EmployeeDto();
	        employeeDto1.setId(1L);
	        employeeDto1.setFirstName("John");
	        employeeDto1.setLastName("Doe");
	        EmployeeDto employeeDto2 = new EmployeeDto();
	        employeeDto2.setId(2L);
	        employeeDto2.setFirstName("Jane");
	        employeeDto2.setLastName("Smith");
	        when(employeeMapper.convertToDto(employee1)).thenReturn(employeeDto1);
	        when(employeeMapper.convertToDto(employee2)).thenReturn(employeeDto2);
	
	        // Test the getAllEmployees method
	        List<EmployeeDto> result = employeeService.getAllEmployees();
	
	        // Verify that the findAll method was called
	        verify(employeeRepository).findAll();
	
	        // Verify that the list of EmployeeDtos returned is as expected
	        assertEquals(2, result.size());
	        assertEquals(1L, result.get(0).getId());
	        assertEquals("John", result.get(0).getFirstName());
	        assertEquals("Doe", result.get(0).getLastName());
	        assertEquals(2L, result.get(1).getId());
	        assertEquals("Jane", result.get(1).getFirstName());
	        assertEquals("Smith", result.get(1).getLastName());
    	}catch(Throwable t) {
    		fail(t.getMessage());
    	}
    }

    // Test the getEmployeeById method
    @Test
    public void testGetEmployeeById() {
    	try {
	        // Create a mock Employee entity
	        Employee employee = new Employee();
	        employee.setId(1L);
	        employee.setFirstName("John");
	        employee.setLastName("Doe");
	
	        // Mock the behavior of the employeeRepository.findById method
	        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
	
	        // Mock the behavior of the employeeMapper
	        EmployeeDto employeeDto = new EmployeeDto();
	        employeeDto.setId(1L);
	        employeeDto.setFirstName("John");
	        employeeDto.setLastName("Doe");
	        when(employeeMapper.convertToDto(employee)).thenReturn(employeeDto);
	
	        // Test the getEmployeeById method
	        EmployeeDto result = employeeService.getEmployeeById(1L);
	
	        // Verify that the findById method was called with the correct ID
	        verify(employeeRepository).findById(1L);
	
	        // Verify that the EmployeeDto returned is as expected
	        assertEquals(1L, result.getId());
	        assertEquals("John", result.getFirstName());
	        assertEquals("Doe", result.getLastName());
    	}catch(Throwable t) {
    		fail(t.getMessage());
    	}
    }

}
