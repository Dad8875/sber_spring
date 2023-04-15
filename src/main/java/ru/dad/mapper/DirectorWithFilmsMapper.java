package ru.dad.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.dad.dto.DirectorWithFilmsDTO;
import ru.dad.model.Director;
import ru.dad.model.GenericModel;
import ru.dad.repository.FilmRepository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DirectorWithFilmsMapper extends GenericMapper<Director, DirectorWithFilmsDTO> {

    private final FilmRepository filmRepository;

    protected DirectorWithFilmsMapper(ModelMapper modelMapper, FilmRepository filmRepository) {
        super(modelMapper, Director.class, DirectorWithFilmsDTO.class);
        this.filmRepository = filmRepository;
    }

    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(Director.class, DirectorWithFilmsDTO.class)
                .addMappings(m -> m.skip(DirectorWithFilmsDTO::setFilmIds)).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(DirectorWithFilmsDTO.class, Director.class)
                .addMappings(m -> m.skip(Director::setFilms)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(DirectorWithFilmsDTO source, Director destination) {
        destination.setFilms(new HashSet<>(filmRepository.findAllById(source.getFilmIds())));
    }

    @Override
    protected void mapSpecificFields(Director source, DirectorWithFilmsDTO destination) {
        destination.setFilmIds(getIds(source));
    }

    @Override
    protected Set<Long> getIds(Director director) {
        return Objects.isNull(director) || Objects.isNull(director.getId())
                ? null : director.getFilms().stream()
                .map(GenericModel::getId).collect(Collectors.toSet());
    }
}
