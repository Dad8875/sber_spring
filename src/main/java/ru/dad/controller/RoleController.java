package ru.dad.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import ru.dad.model.Role;
import ru.dad.repository.RoleRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/roles")
@Tag(name = "Роли пользователей", description = "Контроллер для работы с ролями пользователей")
public class RoleController {
    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Operation(description = "Список ролей", method = "getAll")
    @RequestMapping(value = "/allRoles", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Role>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(roleRepository.findAll());
    }

    @Operation(description = "Получение роли по ID", method = "getOneById")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> getOneById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Нет записи по переданному id " + id)));
    }

    @Operation(description = "Создание новой роли", method = "create")
    @RequestMapping(value = "/create", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> create(@RequestBody Role role) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleRepository.save(role));
    }

    @Operation(description = "Обновлнеие роли", method = "update")
    @RequestMapping(value = "/update", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> update(@RequestBody Role role, @RequestParam(name = "id") Long id) {
        role.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(roleRepository.save(role));
    }

    @Operation(description = "Удаление роли по ID", method = "delete")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") Long id) {
        roleRepository.deleteById(id);
    }
}
