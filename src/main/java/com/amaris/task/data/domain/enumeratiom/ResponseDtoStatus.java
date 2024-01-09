package com.amaris.task.data.domain.enumeratiom;

public enum ResponseDtoStatus{
	SUCCESS(1), 
	ERROR(0);
    
	private Integer code;
	
	private ResponseDtoStatus(int c) {
		code = c;
	}

	public Integer getCode() {
		return code;
	}

}
