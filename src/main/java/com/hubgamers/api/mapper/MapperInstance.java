package com.hubgamers.api.mapper;

import org.modelmapper.ModelMapper;


public abstract class MapperInstance {
	protected static final ModelMapper modelMapper = new ModelMapper();

	public static ModelMapper getModelMapper() {
		return modelMapper;
	}

	abstract Class<?> getEntityClass();

	abstract Class<?> getDTOClass();
}
