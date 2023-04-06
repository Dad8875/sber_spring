package ru.dad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dad.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
