package com.amaris.task.data.domain.mapper;

import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import com.amaris.task.common.exception.ModelProcessingException;
import com.amaris.task.common.model.ModelConverter;
import com.amaris.task.data.domain.dto.EmployeeDto;
import com.amaris.task.data.orm.entity.Employee;

/** @author salvati.cristiano_am
 * 
 * a mapper between Employee Dtos and entities
 */

@Component
public class EmployeeDtoMapper extends ModelConverter<Employee, EmployeeDto> {

	public EmployeeDtoMapper() throws ModelProcessingException {
		super();
	}

	@Override
	protected void configureMappings(String confParameter) throws ModelProcessingException {
		TypeMap<Employee, EmployeeDto> entityTypeMap = 
				getEntityMapper().createTypeMap(Employee.class, EmployeeDto.class);

//	    Mapping configuration to convert Entity to DTO
	    entityTypeMap.addMappings(new PropertyMap<Employee, EmployeeDto>() {
	    	@Override
	        protected void configure() {		
	    		/**
	    		 TODO: extend here mapping behavior
	    		 */
	        }
	    });
	}

	@Override
	public EmployeeDto convertToDto(Employee entity) throws ModelProcessingException {
		try {
			return getEntityMapper().map(entity, EmployeeDto.class);
		}catch(Throwable t) {
			throw new ModelProcessingException(t);
		}
	}

	@Override
	public Employee convertToEntity(EmployeeDto dto) throws ModelProcessingException {
		try {
			return getEntityMapper().map(dto, Employee.class);
		}catch(Throwable t) {
			throw new ModelProcessingException(t);
		}
	}

}
