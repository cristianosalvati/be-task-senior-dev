package com.amaris.task.data.orm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amaris.task.data.orm.entity.Task;

/**
 * @author  salvati.cristiano_am
 * 
 * a standard jpa repository for the Task entity 
 */

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
