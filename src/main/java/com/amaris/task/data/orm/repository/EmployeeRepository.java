package com.amaris.task.data.orm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amaris.task.data.orm.entity.Employee;

/**
 * @author  salvati.cristiano_am
 * 
 * a standard jpa repository for the Employee entity 
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
     
}

