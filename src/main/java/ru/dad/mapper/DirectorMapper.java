package ru.dad.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.dad.dto.DirectorDTO;
import ru.dad.model.Director;
import ru.dad.model.GenericModel;
import ru.dad.repository.FilmRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DirectorMapper extends GenericMapper<Director, DirectorDTO> {

    private final FilmRepository filmRepository;

    protected DirectorMapper(ModelMapper modelMapper, FilmRepository filmRepository) {
        super(modelMapper, Director.class, DirectorDTO.class);
        this.filmRepository = filmRepository;
    }

    @PostConstruct
    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(Director.class, DirectorDTO.class)
                .addMappings(m -> m.skip(DirectorDTO::setFilmIds)).setPostConverter(toDTOConverter());

        modelMapper.createTypeMap(DirectorDTO.class, Director.class)
                .addMappings(m -> m.skip(Director::setFilms)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(DirectorDTO source, Director destination) {
        if (!Objects.isNull(source.getFilmIds())) {
            destination.setFilms(new HashSet<>(filmRepository.findAllById(source.getFilmIds())));
        } else {
            destination.setFilms(Collections.emptySet());
        }
    }

    @Override
    protected void mapSpecificFields(Director source, DirectorDTO destination) {
        destination.setFilmIds(getIds(source));
    }

    @Override
    protected Set<Long> getIds(Director director) {
        return Objects.isNull(director) || Objects.isNull(director.getFilms())
                ? null : director.getFilms().stream()
                .map(GenericModel::getId).collect(Collectors.toSet());
    }
}
