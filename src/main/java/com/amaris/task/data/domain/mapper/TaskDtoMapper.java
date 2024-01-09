package com.amaris.task.data.domain.mapper;

import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import com.amaris.task.common.exception.ModelProcessingException;
import com.amaris.task.common.model.ModelConverter;
import com.amaris.task.data.domain.dto.EmployeeDto;
import com.amaris.task.data.domain.dto.TaskDto;
import com.amaris.task.data.orm.entity.Employee;
import com.amaris.task.data.orm.entity.Task;

/** @author salvati.cristiano_am
 * 
 * a mapper between Tasks Dtos and entities
 */

@Component
public class TaskDtoMapper extends ModelConverter<Task, TaskDto> {

	public TaskDtoMapper() throws ModelProcessingException {
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
	public TaskDto convertToDto(Task entity) throws ModelProcessingException {
		try {
			return getEntityMapper().map(entity, TaskDto.class);
		}catch(Throwable t) {
			throw new ModelProcessingException(t);
		}
	}

	@Override
	public Task convertToEntity(TaskDto dto) throws ModelProcessingException {
		try {
			return getEntityMapper().map(dto, Task.class);
		}catch(Throwable t) {
			throw new ModelProcessingException(t);
		}
	}

}
