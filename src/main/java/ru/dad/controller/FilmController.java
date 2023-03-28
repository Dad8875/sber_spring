package ru.dad.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dad.model.Film;
import ru.dad.repository.FilmRepository;

@RestController
@RequestMapping(value = "/films")
@Tag(name = "Фильмы", description = "Контроллер для работы с фильмами")
public class FilmController extends GenericController<Film> {

    private final FilmRepository filmRepository;

    public FilmController (FilmRepository filmRepository) {
        super(filmRepository);
        this.filmRepository = filmRepository;
    }
}
