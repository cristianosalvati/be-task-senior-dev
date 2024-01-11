package com.amaris.task.data.orm.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amaris.task.common.exception.ModelProcessingException;
import com.amaris.task.common.exception.RepositoryException;
import com.amaris.task.data.domain.dto.EmployeeDto;
import com.amaris.task.data.domain.dto.EmployeeTaskDto;
import com.amaris.task.data.domain.dto.SearchFilterDto;
import com.amaris.task.data.domain.dto.TaskDto;
import com.amaris.task.data.domain.mapper.EmployeeDtoMapper;
import com.amaris.task.data.domain.mapper.TaskDtoMapper;
import com.amaris.task.data.orm.entity.Employee;
import com.amaris.task.data.orm.entity.EmployeeTask;
import com.amaris.task.data.orm.entity.Task;

/**
 * @author  salvati.cristiano_am
 * 
 * A repository with a custom JPA implementation to filter and retrieve 
 * employers related to a task with some optional parameter selection   
 */

@Repository	
public class FilterRepository {

	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

	@Autowired
	private EmployeeDtoMapper employeeMapper;
	@Autowired
	
	private TaskDtoMapper taskMapper;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
    public  EmployeeTaskDto searchByFilter(SearchFilterDto filter) throws RepositoryException, ModelProcessingException{
    	try {
	    	EntityManager entityManager = getEntityManager();
	    	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    	
	    	CriteriaQuery<Tuple> query = builder.createTupleQuery();
			Root <Task> rootTask = query.from(Task.class);
			Join<EmployeeTask, Task> joinEmployeeTask = rootTask.join("employeeTasks", JoinType.LEFT);	
			Join<EmployeeTask, Employee> joinEmployee = joinEmployeeTask.join("employee", JoinType.LEFT);
			
			ArrayList<Predicate> conditions = new ArrayList<>();
			
			conditions.add(builder.isNull(rootTask.<Date>get("dateDelete")));
			conditions.add(builder.isNull(joinEmployeeTask.<Date>get("dateDelete")));
			conditions.add(builder.isNull(joinEmployee.<Date>get("dateDelete")));
			
	//		filter by TASK ID
			if (filter.getTaskId() != null && filter.getTaskId() > 0) 
				conditions.add(builder.equal(rootTask.<Integer>get("id"), filter.getTaskId()));
	//		filter by TASK title
			if (filter.getTitle() != null && !filter.getTitle().isEmpty()) 
				conditions.add(builder.equal(rootTask.<String>get("title"), filter.getTitle() ));	
	//		filter by task descriptions
			if (filter.getDescription() != null && !filter.getDescription().isEmpty()) 
				conditions.add(builder.equal(rootTask.<String>get("description"), filter.getDescription() ));	
	//		filter by task Status 
			if (filter.getTaskStatus() != null) 
				conditions.add(builder.equal(rootTask.<String>get("taskStatus"), filter.getTaskStatus() ));	
			
	//		filter employee's last name		
			if (filter.getLastName() != null && !filter.getLastName().isEmpty()) 
				conditions.add(builder.equal(joinEmployee.<String>get("lastName"), filter.getLastName()));
	//		filter employee's role	
			if (filter.getRole() != null && !filter.getRole().isEmpty()) 
				conditions.add(builder.equal(joinEmployee.<String>get("role"), filter.getRole()));
	//		filter employee's email		
			if (filter.getEmail() != null && !filter.getEmail().isEmpty()) 
				conditions.add(builder.equal(joinEmployee.<String>get("email"), filter.getEmail()));
					
			if (conditions.size() >0)
				query.where(builder.and(conditions.toArray(new Predicate[conditions.size()])));		
			
			query.multiselect(rootTask, joinEmployee);
			List<Tuple> results = entityManager.createQuery(query).getResultList();

			EmployeeTaskDto dto = new EmployeeTaskDto();
	        for (Tuple result : results) {
	        	TaskDto task = taskMapper.convertToDto(result.get(0, Task.class));
	        	EmployeeDto employee = employeeMapper.convertToDto(result.get(1, Employee.class));
	        	
	            dto.add(task, employee);
	        }

	        return dto;
    	}catch(ModelProcessingException e) {
    		throw e;
    	}catch(Throwable t) {
    		throw new RepositoryException(t);
    	}
    }
	
}
