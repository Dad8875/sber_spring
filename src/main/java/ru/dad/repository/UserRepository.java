package ru.dad.repository;

import org.springframework.stereotype.Repository;
import ru.dad.model.User;

@Repository
public interface UserRepository extends GenericRepository<User> {

}
