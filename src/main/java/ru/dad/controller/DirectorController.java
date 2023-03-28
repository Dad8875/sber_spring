package ru.dad.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dad.model.Director;
import ru.dad.repository.DirectorRepository;

@RestController
@RequestMapping(name = "/directors")
@Tag(name = "Режиссеры", description = "Контроллер для работы с режиссерами")
public class DirectorController extends GenericController<Director>{
    private DirectorRepository directorRepository;

    public DirectorController(DirectorRepository directorRepository) {
        super(directorRepository);
        this.directorRepository = directorRepository;
    }
}
