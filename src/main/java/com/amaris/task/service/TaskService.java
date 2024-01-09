package com.amaris.task.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amaris.task.common.exception.ModelProcessingException;
import com.amaris.task.common.exception.RepositoryException;
import com.amaris.task.common.exception.ServiceException;
import com.amaris.task.common.util.Constants;
import com.amaris.task.data.domain.dto.EmployeeTaskDto;
import com.amaris.task.data.domain.dto.SearchFilterDto;
import com.amaris.task.data.domain.dto.TaskDto;
import com.amaris.task.data.domain.mapper.TaskDtoMapper;
import com.amaris.task.data.orm.entity.Employee;
import com.amaris.task.data.orm.entity.EmployeeTask;
import com.amaris.task.data.orm.entity.Task;
import com.amaris.task.data.orm.repository.EmployeeRepository;
import com.amaris.task.data.orm.repository.EmployeeTaskRepository;
import com.amaris.task.data.orm.repository.FilterRepository;
import com.amaris.task.data.orm.repository.TaskRepository;

import lombok.extern.java.Log;

@Service
@Log
public class TaskService {
	@Autowired
    private TaskRepository taskRepository;
	@Autowired
    private EmployeeTaskRepository employeeTaskRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private TaskDtoMapper taskMapper;
    @Autowired
    private FilterRepository filterRepository;
	
    @Transactional
    public TaskDto createTask(TaskDto dto) throws ModelProcessingException{
    	Task entity = taskMapper.convertToEntity(dto);
    	entity.setDateInsert(new Date());
        Task task = taskRepository.save(entity);
        dto.setId(task.getId());;
        log.info(String.format("Create task %s" , dto.toString()));
        return dto;
    }

    @Transactional
    public void updateTask(TaskDto task) throws ModelProcessingException{
    	Task entity = taskMapper.convertToEntity(task);
    	entity.setDateUpdate(new Date());
        taskRepository.save(entity);
    }

    @Transactional
    public TaskDto deleteTask(Long taskId) throws ServiceException, ModelProcessingException {
    	Task t = taskRepository.findById(taskId).orElse(null);
    	if (t == null)
    		throw new ServiceException(String.format("Unable to find task with Id = %d", taskId));
        taskRepository.deleteById(taskId);
        return taskMapper.convertToDto(t);
    }

    public List<TaskDto> getAllTasks() throws ModelProcessingException {
    	List<Task> taskList = taskRepository.findAll();
    	ArrayList<TaskDto> taskListDto = mapEntityListToDtoList(taskList);
    	return taskListDto;
    }

    public TaskDto getTaskById(Long taskId) throws ModelProcessingException {
        Task t = taskRepository.findById(taskId).orElse(null);
        return taskMapper.convertToDto(t);
    }

    @Transactional
    public TaskDto assignTaskToEmployee(Long taskId, Long employeeId) throws ModelProcessingException, ServiceException {
        Task task = taskRepository.findById(taskId).orElse(null);
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        EmployeeTask et = employeeTaskRepository.findByTaskIdAndEmployeeIdAndDateDeleteIsNull(taskId, employeeId);
        
        if (task == null || employee == null || 
        		(et != null && et.getDateDelete() == null)) {
        	throw new ServiceException(String.format("Unable to assign task with Id = %d to employee = %d", taskId, employeeId));
        }else {
        	 EmployeeTask employeeTask = new EmployeeTask();
             employeeTask.setTask(task);
             employeeTask.setEmployee(employee);
             employeeTask.setDateInsert(new Date());
             employeeTask.setUserUpdate(Constants.DEFAULT_USER_NAME);
             employeeTaskRepository.save(employeeTask);
             log.info(String.format("assign task %d to employee %d" , taskId, employeeId));
             return taskMapper.convertToDto(task);	
        }
    }
    
    @Transactional
	public TaskDto unassignTaskFromEmployee(Long taskId, Long employeeId) throws ModelProcessingException, ServiceException {
		EmployeeTask et = employeeTaskRepository.findByTaskIdAndEmployeeIdAndDateDeleteIsNull(taskId, employeeId);
		if (et != null) {
			// Update delete date on assing
			et.setDateDelete(new Date());
			
			//Update date_update on related task
			Task task = et.getTask();
			task.setDateUpdate(new Date());
			task.setUserUpdate(Constants.DEFAULT_USER_NAME);
			employeeTaskRepository.save(et);
			log.info(String.format("task %d unassigned to employee %d" , taskId, employeeId));
			return taskMapper.convertToDto(task);
		}
		throw new ServiceException(String.format("Unable to unassign task with Id = %d to employee = %d", taskId, employeeId));
	}
    
    @Transactional
    public EmployeeTaskDto findTasksBySearchFilter(SearchFilterDto dto) throws ModelProcessingException, RepositoryException {
    	EmployeeTaskDto taskListDto = filterRepository.searchByFilter(dto);
    	
    	return taskListDto;  	
    }
    
    @Transactional
	public TaskDto updateTaskDueDate(Long taskId, Date newDueDate) throws ServiceException, ModelProcessingException {
    	 Task task = taskRepository.findById(taskId).orElse(null);
    	 if (task != null) {
    		 task.setDueDate(newDueDate);
    		 task.setDateUpdate(new Date());
    		 taskRepository.save(task);
    		 
    		 log.info(String.format("task %d update on due date" , taskId));
    		 return taskMapper.convertToDto(task);
    	 }
    	 throw new ServiceException(String.format("Unable to find task with Id = %d", taskId));
	}

	private ArrayList<TaskDto> mapEntityListToDtoList(List<Task> taskList) throws ModelProcessingException {
		ArrayList<TaskDto> taskListDto = new ArrayList<TaskDto>();
    	for (Task t : taskList) {
    		taskListDto.add(
    				taskMapper.convertToDto(t));
    	}
		return taskListDto;
	}

}

