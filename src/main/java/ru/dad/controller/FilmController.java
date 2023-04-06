package ru.dad.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dad.dto.FilmDTO;
import ru.dad.model.Film;
import ru.dad.service.FilmService;

import java.util.List;

@RestController
@RequestMapping(value = "/films")
@Tag(name = "Фильмы", description = "Контроллер для работы с фильмами")
public class FilmController extends GenericController<Film, FilmDTO> {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        super(filmService);
        this.filmService = filmService;
    }

    @Operation(description = "Добавить режиссера к фильму", method = "addDirectorToFilm")
    @RequestMapping(value = "/addDirector", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FilmDTO> addDirectorToFilm(@RequestParam(name = "filmId") Long filmId,
                                                     @RequestParam(name = "directorId") Long directorId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(filmService.addDirector(filmId, directorId));
    }

    @Operation(description = "Все арендованные фильмы пользователя", method = "allRentFilmsUser")
    @RequestMapping(value = "/allRentFilm", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FilmDTO>> allRentFilmsUser(@RequestParam(name = "userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(filmService.getAllOrders(userId));
    }
}
