package ru.dad.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dad.model.Order;
import ru.dad.repository.OrderRepository;
@RestController
@RequestMapping("/orders")
@Tag(name = "Заказы", description = "Контроллер по работе с заказами")
public class OrderController extends GenericController<Order>{
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        super(orderRepository);
        this.orderRepository = orderRepository;
    }
}
