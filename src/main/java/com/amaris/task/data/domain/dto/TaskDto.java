package com.amaris.task.data.domain.dto;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TaskDto extends AbstractDto{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;
	private String description;
	private String taskStatus;
	private Date dueDate;
	private EmployeeDto manager;
	private Date dateInsert;
	private Date dateDelete;
	private Date dateUpdate;
	private String userUpdate;
	private List<EmployeeDto> employees;
	
	public boolean equals(Object o) {
	    if (this == o) {
	        return true;
	    }
	    if (o == null || getClass() != o.getClass()) {
	        return false;
	    }
	    TaskDto other = (TaskDto) o;
	    return id != null && other.id != null && id.equals(other.id);
	}

}
