package ru.dad.service;

import org.springframework.stereotype.Service;
import ru.dad.dto.UserDTO;
import ru.dad.mapper.UserMapper;
import ru.dad.model.User;
import ru.dad.repository.UserRepository;

@Service
public class UserService extends GenericService<User, UserDTO> {
    protected UserRepository userRepository;

    protected UserService(UserRepository userRepository, UserMapper userMapper) {
        super(userRepository, userMapper);
        this.userRepository = userRepository;
    }
}
