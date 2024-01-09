package com.amaris.task.data.domain.dto;

import com.amaris.task.data.domain.enumeratiom.TaskStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchFilterDto {

	private Long taskId;
	private String title;
	private String description;
	private String lastName;
	private String role;
	private String email;
	private TaskStatus taskStatus;

}
