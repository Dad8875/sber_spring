package ru.dad.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dad.model.GenreFilm;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmDTO extends GenericDTO {
    private String title;
    private String premierYear;
    private String country;
    private GenreFilm genre;
    private Set<Long> directorsIds;
}
