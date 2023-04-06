package ru.dad.mapper;

import ru.dad.dto.GenericDTO;
import ru.dad.model.GenericModel;

import java.util.List;

public interface Mapper<E extends GenericModel, D extends GenericDTO> {
    E toEntity(D dto);

    D toDTO(E entity);

    List<E> toEntities(List<D> listDTO);

    List<D> toDTOs(List<E> listEntity);
}
