package ru.dad.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.dad.dto.FilmWithDirectorsDTO;
import ru.dad.model.Film;
import ru.dad.model.GenericModel;
import ru.dad.repository.DirectorRepository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilmWithDirectorsMapper extends GenericMapper<Film, FilmWithDirectorsDTO> {
    private final DirectorRepository directorRepository;

    protected FilmWithDirectorsMapper(ModelMapper modelMapper, DirectorRepository directorRepository) {
        super(modelMapper, Film.class, FilmWithDirectorsDTO.class);
        this.directorRepository = directorRepository;
    }

    @Override
    @PostConstruct
    protected void setupMapper() {
        modelMapper.createTypeMap(Film.class, FilmWithDirectorsDTO.class)
                .addMappings(m -> m.skip(FilmWithDirectorsDTO::setDirectorsIds)).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(FilmWithDirectorsDTO.class, Film.class)
                .addMappings(m -> m.skip(Film::setDirectors)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(FilmWithDirectorsDTO source, Film destination) {
        destination.setDirectors(new HashSet<>(directorRepository.findAllById(source.getDirectorsIds())));
    }

    @Override
    protected void mapSpecificFields(Film source, FilmWithDirectorsDTO destination) {
        destination.setDirectorsIds(getIds(source));
    }

    @Override
    protected Set<Long> getIds(Film film) {
        return Objects.isNull(film) || Objects.isNull(film.getId())
                ? null : film.getDirectors().stream()
                .map(GenericModel::getId).collect(Collectors.toSet());
    }
}
