package ru.dad.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dad.dto.OrderDTO;
import ru.dad.model.Order;
import ru.dad.service.OrderService;

@RestController
@RequestMapping("/orders")
@Tag(name = "Заказы", description = "Контроллер по работе с заказами")
public class OrderController extends GenericController<Order, OrderDTO> {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        super(orderService);
        this.orderService = orderService;
    }
    @Operation(description = "Арендовать фильм", method = "rentFilm")
    @RequestMapping(value = "/rentFilm", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> rentFilm(@RequestParam(name = "userId")Long userId,
                                             @RequestParam(name = "filmId")Long filmId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.addOrder(userId, filmId));
    }
}
