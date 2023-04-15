package ru.dad.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.dad.dto.DirectorDTO;
import ru.dad.dto.DirectorWithFilmsDTO;
import ru.dad.mapper.DirectorMapper;
import ru.dad.mapper.DirectorWithFilmsMapper;
import ru.dad.model.Director;
import ru.dad.repository.DirectorRepository;

import java.util.List;

@Service
public class DirectorService extends GenericService<Director, DirectorDTO> {
    private final DirectorRepository directorRepository;
    private final DirectorWithFilmsMapper directorWithFilmsMapper;


    public DirectorService(DirectorRepository directorRepository, DirectorMapper directorMapper, DirectorWithFilmsMapper directorWithFilmsMapper) {
        super(directorRepository, directorMapper);
        this.directorRepository = directorRepository;
        this.directorWithFilmsMapper = directorWithFilmsMapper;
    }

    //добавить фильм режиссеру
    public DirectorDTO addFilm(Long directorId, Long filmId) {
        DirectorDTO directorDTO = genericMapper.toDTO(directorRepository.findById(directorId)
                .orElseThrow(() -> new NotFoundException("Нет записи по переданному ID " + directorId)));
        directorDTO.getFilmIds().add(filmId);

        return genericMapper.toDTO(directorRepository.save(genericMapper.toEntity(directorDTO)));
    }

    public List<DirectorWithFilmsDTO> getAllDirectorWithFilms() {
        return directorWithFilmsMapper.toDTOs(directorRepository.findAll());
    }
}
