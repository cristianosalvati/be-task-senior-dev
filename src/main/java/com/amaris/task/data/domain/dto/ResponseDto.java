package com.amaris.task.data.domain.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.amaris.task.data.domain.enumeratiom.ResponseDtoStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseDto<T extends AbstractDto> implements Serializable {

	private static final long serialVersionUID = 1L;

	private ResponseDtoStatus status;
	private List<T> items;
	
	@SuppressWarnings("unchecked")
	public void addResponseData(AbstractDto dto) {
		if (items == null) 
			items = new ArrayList<T>();
		items.add((T) dto);
	}

}
