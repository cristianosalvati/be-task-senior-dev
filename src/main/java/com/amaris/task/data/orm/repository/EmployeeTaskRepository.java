package com.amaris.task.data.orm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amaris.task.data.orm.entity.EmployeeTask;
import com.amaris.task.data.orm.entity.EmployeeTaskFk;

@Repository
public interface EmployeeTaskRepository extends JpaRepository<EmployeeTask, EmployeeTaskFk> {
	
	public EmployeeTask findByTaskIdAndEmployeeIdAndDateDeleteIsNull(Long taskId, Long employeeId);
}
