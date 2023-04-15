package ru.dad.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dad.dto.GenericDTO;
import ru.dad.model.GenericModel;
import ru.dad.service.GenericService;

import java.time.LocalDateTime;
import java.util.List;

// @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
public abstract class GenericController<T extends GenericModel, N extends GenericDTO> {

    private final GenericService<T, N> genericService;

    public GenericController(GenericService<T, N> genericService) {
        this.genericService = genericService;
    }

    @Operation(description = "Получение всех записей", method = "getAll")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<N>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(genericService.listAllDTO());
    }

    @Operation(description = "Получение записи по ID", method = "getOneById")
    @RequestMapping(value = "/getById", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<N> getOneById(@RequestParam(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(genericService.getOneDTOById(id));
    }

    @Operation(description = "Добавление записи", method = "create")
    @RequestMapping(value = "/add", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<N> create(@RequestBody N n) {
        n.setCreateWhen(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(genericService.create(n));
    }

    @Operation(description = "Обновление записи", method = "update")
    @RequestMapping(value = "/update", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<N> update(@RequestBody N n, @RequestParam(name = "id") Long id) {
        n.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(genericService.updateDTO(n));
    }

    @Operation(description = "Удаление записи", method = "delete")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") Long id) {
        genericService.delete(id);
    }
}
//02:07:12!