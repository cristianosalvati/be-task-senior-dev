package com.amaris.task.data.orm.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Embeddable
public class EmployeeTaskFk implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private Long employee;
    private Long task;
    private Date dateInsert;
    
}