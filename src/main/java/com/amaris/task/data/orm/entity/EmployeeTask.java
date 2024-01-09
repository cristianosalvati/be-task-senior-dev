package com.amaris.task.data.orm.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Employee_Task")
@Getter
@Setter
@IdClass(EmployeeTaskFk.class)
public class EmployeeTask implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
	
	@Id
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
	
	@Id
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_insert", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date dateInsert;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_delete")
    private Date dateDelete;

    @Column(name = "user_update")
    private String userUpdate;
    
}
