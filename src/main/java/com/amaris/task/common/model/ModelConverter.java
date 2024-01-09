package com.amaris.task.common.model;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;

import com.amaris.task.common.exception.ModelProcessingException;
import com.amaris.task.data.domain.dto.AbstractDto;

/** @author Cristiano
 *  Abstract class to define a generic converter to map Entity to Dto and vice versa.
 */

public abstract class  ModelConverter<E, D extends AbstractDto> {
 
	private final ModelMapper entityMapper;
	private final ModelMapper dtoMapper;
	private TypeMap<E, D> configuredEntityTypeMap;

    public ModelConverter() throws ModelProcessingException{
        this.entityMapper = new ModelMapper();
        this.dtoMapper = new ModelMapper();
        getEntityMapper().getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        getDtoMapper().getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        configureMappings(null);
    }
    
    public ModelConverter(String confParameter) throws ModelProcessingException {
    	if (confParameter == null || confParameter.isEmpty())
    		throw new ModelProcessingException("parameter in constructor have cannot be null or empty!");
        this.entityMapper = new ModelMapper();
        this.dtoMapper = new ModelMapper();
        configureMappings(confParameter);
    }
    
    public ModelMapper getEntityMapper(){
    	return entityMapper;
    }
    
    public ModelMapper getDtoMapper(){
    	return dtoMapper;
    }
    
    public TypeMap<E, D> getConfiguredEntityTypeMap(){
    	return this.configuredEntityTypeMap;
    }
    
	public abstract D convertToDto(E entity) throws ModelProcessingException;
	
	public abstract E convertToEntity(D dto) throws ModelProcessingException;

	protected abstract void configureMappings(String confParameter) throws ModelProcessingException;
}