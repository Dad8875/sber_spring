package ru.dad.controllerMVC;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dad.dto.DirectorDTO;
import ru.dad.dto.DirectorWithFilmsDTO;
import ru.dad.service.DirectorService;

import java.util.List;

@Controller
@RequestMapping("/directors")
public class MVCDirectorController {

    private final DirectorService directorService;

    public MVCDirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping("")
    public String getAllDirectors(Model model) {
        List<DirectorWithFilmsDTO> directorDTOS = directorService.getAllDirectorWithFilms();
        model.addAttribute("directors", directorDTOS);

        return "directors/viewAllDirectors";
    }

    @GetMapping("/add")
    public String create() {
        return "directors/addDirector";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("directorForm") DirectorDTO directorDTO) {
        directorService.create(directorDTO);
        return "redirect:/directors";
    }
}
