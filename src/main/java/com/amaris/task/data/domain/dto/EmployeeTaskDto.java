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
	private ArrayList<TaskDto> employeeTaskList;
	
	public EmployeeTaskDto() {
		super();
		id = new Date().getTime();
		employeeTaskList = new ArrayList<TaskDto> ();
	}
	
	public void add(TaskDto t, EmployeeDto e) {
		if (!employeeTaskList.contains(t)) {
			employeeTaskList.add(t);
			t.setEmployees(new ArrayList<EmployeeDto>() );
			t.getEmployees().add(e);
		}else {
			employeeTaskList.get(employeeTaskList.indexOf(t))
				.getEmployees().add(e);
		}
		
	}

}
