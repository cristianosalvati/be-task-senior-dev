package com.amaris.task.data.domain.dto;

import java.util.Date;

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
}
