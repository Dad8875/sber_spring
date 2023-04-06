package ru.dad.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dad.dto.UserDTO;
import ru.dad.model.User;
import ru.dad.service.UserService;

@RestController
@RequestMapping(value = "/users")
@Tag(name = "Пользователи сервиса", description = "Контроллер для работы с пользователями")
public class UserController extends GenericController<User, UserDTO> {

    public UserController(UserService userService) {
        super(userService);
    }
}
