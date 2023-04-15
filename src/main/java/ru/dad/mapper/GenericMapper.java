package ru.dad.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.dad.dto.GenericDTO;
import ru.dad.model.GenericModel;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
public abstract class GenericMapper<E extends GenericModel, D extends GenericDTO>
        implements Mapper<E, D> {

    protected final ModelMapper modelMapper;
    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    protected GenericMapper(ModelMapper modelMapper, Class<E> entityClass, Class<D> dtoClass) {
        this.modelMapper = modelMapper;
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public E toEntity(D dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, entityClass);
    }

    @Override
    public List<E> toEntities(List<D> listDTO) {
        return listDTO.stream().map(this::toEntity).toList();
    }

    @Override
    public D toDTO(E entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, dtoClass);
    }

    @Override
    public List<D> toDTOs(List<E> listEntity) {
        return listEntity.stream().map(this::toDTO).toList();
    }

    Converter<E, D> toDTOConverter() {
        return context -> {
            E source = context.getSource();
            D destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    Converter<D, E> toEntityConverter() {
        return context -> {
            D source = context.getSource();
            E destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    @PostConstruct
    protected abstract void setupMapper();

    protected abstract void mapSpecificFields(D source, E destination);

    protected abstract void mapSpecificFields(E source, D destination);

    protected abstract Set<Long> getIds(E e);
}
