package ru.dad.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.dad.dto.GenericDTO;
import ru.dad.mapper.GenericMapper;
import ru.dad.model.GenericModel;
import ru.dad.repository.GenericRepository;

import java.util.List;

@Service
public abstract class GenericService<T extends GenericModel, N extends GenericDTO> {
    protected final GenericRepository<T> genericRepository;
    protected final GenericMapper<T, N> genericMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public GenericService(GenericRepository<T> genericRepository, GenericMapper<T, N> genericMapper) {
        this.genericRepository = genericRepository;
        this.genericMapper = genericMapper;
    }

    public List<N> listAllDTO() {
        return genericMapper.toDTOs(genericRepository.findAll());
    }

    public N getOneDTOById(Long id) {
        return genericMapper.toDTO(genericRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Нет записи по переданному ID " + id)));
    }

    public N create(N n) {
        return genericMapper.toDTO(genericRepository.save(genericMapper.toEntity(n)));
    }

    public N updateDTO(N n) {
        return genericMapper.toDTO(genericRepository.save(genericMapper.toEntity(n)));
    }

    public void delete(Long id) {
        genericRepository.deleteById(id);
    }
}
