package com.amaris.task.data.domain.dto;

import java.util.Date;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ErrorDto extends AbstractDto{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String description;
	private Set<StackTraceElement> stackTrace;
	
	public ErrorDto() {
		this.id = new Date().getTime();
	}
 
}
