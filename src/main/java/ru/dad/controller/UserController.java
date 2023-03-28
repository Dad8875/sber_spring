package ru.dad.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dad.model.User;
import ru.dad.repository.UserRepository;

@RestController
@RequestMapping(value = "/users")
@Tag(name = "Пользователи сервиса", description = "Контроллер для работы с пользователями")
public class UserController extends GenericController<User>{
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }
}
