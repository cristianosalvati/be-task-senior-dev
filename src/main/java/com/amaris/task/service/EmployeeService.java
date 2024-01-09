package com.amaris.task.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amaris.task.common.exception.ModelProcessingException;
import com.amaris.task.common.exception.ServiceException;
import com.amaris.task.data.domain.dto.EmployeeDto;
import com.amaris.task.data.domain.mapper.EmployeeDtoMapper;
import com.amaris.task.data.orm.entity.Employee;
import com.amaris.task.data.orm.repository.EmployeeRepository;

import lombok.extern.java.Log;

@Service
@Log
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
	private EmployeeDtoMapper employeeMapper;

    @Transactional
    public EmployeeDto createEmployee(EmployeeDto dto) throws ModelProcessingException {
    	Employee entity = employeeMapper.convertToEntity(dto);
    	entity.setDateInsert(new Date());
    	entity.setUserUpdate("admin");
    	Employee employee = employeeRepository.save(entity);
    	dto.setId(employee.getId());
    	log.info(String.format("Create employee %s" , dto.toString()));
    	return dto;
    }

    @Transactional
    public void updateEmployee(EmployeeDto dto) throws ModelProcessingException {
    	Employee entity = employeeMapper.convertToEntity(dto);
        employeeRepository.save(entity);
    }

    @Transactional
    public EmployeeDto deleteEmployee(Long employeeId) throws ServiceException, ModelProcessingException {
    	Employee e = employeeRepository.findById(employeeId).orElse(null);
    	if (e == null)
    		throw new ServiceException(String.format("Unable to find employee with Id = %d", employeeId));
        employeeRepository.deleteById(employeeId);
        return employeeMapper.convertToDto(e);
    }

    public List<EmployeeDto> getAllEmployees() throws ModelProcessingException {    
        List<Employee> entityList = employeeRepository.findAll();
    	ArrayList<EmployeeDto> listDto = new ArrayList<EmployeeDto>();
    	for (Employee t : entityList) {
    		listDto.add(
    				employeeMapper.convertToDto(t));
    	}
    	return listDto;
    }

    public EmployeeDto getEmployeeById(Long employeeId) throws ModelProcessingException {
    	Employee entity = employeeRepository.findById(employeeId).orElse(null);
		return employeeMapper.convertToDto(entity);
    }
    
}
