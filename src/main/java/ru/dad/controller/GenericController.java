package ru.dad.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import ru.dad.model.GenericModel;
import ru.dad.repository.GenericRepository;

import java.util.List;

// @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
public abstract class GenericController<T extends GenericModel> {

    private final GenericRepository<T> repository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public GenericController(GenericRepository<T> repository) {
        this.repository = repository;
    }

    @Operation(description = "Получение всех записей", method = "getAll")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    @Operation(description = "Получение записи по ID", method = "getOneById")
    @RequestMapping(value = "/getById", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> getOneById(@RequestParam(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("По данному id '" + id + "' записей не найдено")));
    }

    @Operation(description = "Добавление записи", method = "create")
    @RequestMapping(value = "/add", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> create(@RequestBody T t) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(t));
    }

    @Operation(description = "Обновление записи", method = "update")
    @RequestMapping(value = "/update", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> update(@RequestBody T t, @RequestParam(name = "id") Long id) {
        t.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(t));
    }

    @Operation(description = "Удаление записи", method = "delete")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") Long id) {
        repository.deleteById(id);
    }
}
