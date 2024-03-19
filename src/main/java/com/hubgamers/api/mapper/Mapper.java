package com.hubgamers.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<E, D> {
    default D toDTO(E entity) {
        return MapperInstance.getModelMapper().map(entity, getDTOClass());
    }

    default List<D> toDTO(List<E> entityList) {
        return entityList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    default E toEntity(D dto) {
        return MapperInstance.getModelMapper().map(dto, getEntityClass());
    }

    default List<E> toEntity(List<D> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    Class<E> getEntityClass();

    Class<D> getDTOClass();
    
    List<String> getColumns();
}


