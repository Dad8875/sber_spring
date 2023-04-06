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
import ru.dad.dto.DirectorDTO;
import ru.dad.model.Director;
import ru.dad.service.DirectorService;

@RestController
@RequestMapping(value = "/directors")
@Tag(name = "Режиссеры", description = "Контроллер для работы с режиссерами")
public class DirectorController extends GenericController<Director, DirectorDTO> {
    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        super(directorService);
        this.directorService = directorService;
    }
    @Operation(description = "Добавление фильма режиссеру", method = "addFilmToDirector")
    @RequestMapping(value = "/addFilm", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DirectorDTO> addFilmToDirector(@RequestParam(name = "directorId") Long directorId,
                                                      @RequestParam(name = "filmId") Long filmId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(directorService.addFilm(directorId, filmId));
    }
}
