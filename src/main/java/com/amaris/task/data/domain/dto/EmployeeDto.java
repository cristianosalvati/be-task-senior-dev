package com.amaris.task.data.domain.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeDto extends AbstractDto{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String firstName;
	private String lastName;
	private String role;
	private String email;
	private Date dateInsert;
	private Date dateDelete;
	private Date dateUpdate;
	private String userUpdate;
	
}
