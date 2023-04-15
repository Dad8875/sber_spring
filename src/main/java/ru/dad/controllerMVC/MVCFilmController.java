package ru.dad.controllerMVC;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dad.dto.FilmDTO;
import ru.dad.dto.FilmWithDirectorsDTO;
import ru.dad.service.FilmService;

import java.util.List;

@Controller
@RequestMapping("films")
public class MVCFilmController {
    private final FilmService filmService;

    public MVCFilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("")
    public String getAll(Model model) {
        List<FilmWithDirectorsDTO> filmDTOS = filmService.getAllDirectorsWithFilm();
        model.addAttribute("allFilms", filmDTOS);
        return "films/viewAllFilms";
    }

    @GetMapping("/add")
    public String create() {
        return "films/addFilm";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("filmForm") FilmDTO filmDTO) {
        filmService.create(filmDTO);
        return "redirect:/films";
    }
}
