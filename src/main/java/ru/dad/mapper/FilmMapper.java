package ru.dad.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.dad.dto.FilmDTO;
import ru.dad.model.Film;
import ru.dad.model.GenericModel;
import ru.dad.repository.DirectorRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilmMapper extends GenericMapper<Film, FilmDTO> {

    private final ModelMapper modelMapper;
    private final DirectorRepository directorRepository;

    protected FilmMapper(ModelMapper modelMapper, DirectorRepository directorRepository) {
        super(modelMapper, Film.class, FilmDTO.class);
        this.directorRepository = directorRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    protected void setupMapper() {
        modelMapper.createTypeMap(Film.class, FilmDTO.class)
                .addMappings(m -> m.skip(FilmDTO::setDirectorsIds)).setPostConverter(toDTOConverter());

        modelMapper.createTypeMap(FilmDTO.class, Film.class)
                .addMappings(m -> m.skip(Film::setDirectors)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(FilmDTO source, Film destination) {
        if (!Objects.isNull(source.getDirectorsIds())) {
            destination.setDirectors(new HashSet<>(directorRepository.findAllById(source.getDirectorsIds())));
        } else {
            destination.setDirectors(Collections.emptySet());
        }
    }

    @Override
    protected void mapSpecificFields(Film source, FilmDTO destination) {
        destination.setDirectorsIds(getIds(source));
    }

    protected Set<Long> getIds(Film film) {
        return Objects.isNull(film) || Objects.isNull(film.getDirectors())
                ? null : film.getDirectors().stream()
                .map(GenericModel::getId).collect(Collectors.toSet());
    }
}
