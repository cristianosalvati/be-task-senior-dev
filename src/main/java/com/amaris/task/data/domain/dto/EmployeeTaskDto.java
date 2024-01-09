package com.amaris.task.data.domain.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class EmployeeTaskDto extends AbstractDto{

	private static final long serialVersionUID = 1L;
	private Long id;
	private HashMap<Long, HashMap<String, Object>> employeesForTask;
	
	public EmployeeTaskDto() {
		super();
		id = new Date().getTime();
		employeesForTask = new HashMap<Long, HashMap<String, Object>>();
	}
	
	@SuppressWarnings("unchecked")
	public void add(TaskDto t, EmployeeDto e) {
		if (employeesForTask.get(t.getId()) == null) {
			HashMap<String, Object> listMap = new HashMap<String, Object>();
			listMap.put("task", t);
			listMap.put("employees", new ArrayList<EmployeeDto>());
			employeesForTask.put(t.getId(), listMap);
			
		}
		((ArrayList<EmployeeDto>)employeesForTask.get(t.getId()).get("employees")).add(e);
	}
	
	public Long getId() {
		return this.id;
	}

}
