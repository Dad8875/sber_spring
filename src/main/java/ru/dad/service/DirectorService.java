package ru.dad.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.dad.dto.DirectorDTO;
import ru.dad.mapper.DirectorMapper;
import ru.dad.model.Director;
import ru.dad.repository.DirectorRepository;

@Service
public class DirectorService extends GenericService<Director, DirectorDTO> {
    protected DirectorRepository directorRepository;

    public DirectorService(DirectorRepository directorRepository, DirectorMapper directorMapper) {
        super(directorRepository, directorMapper);
        this.directorRepository = directorRepository;
    }

    //добавить фильм режиссеру
    public DirectorDTO addFilm(Long directorId, Long filmId) {
        DirectorDTO directorDTO = genericMapper.toDTO(directorRepository.findById(directorId)
                .orElseThrow(() -> new NotFoundException("Нет записи по переданному ID " + directorId)));
        directorDTO.getFilmIds().add(filmId);

        return genericMapper.toDTO(directorRepository.save(genericMapper.toEntity(directorDTO)));
    }
}
