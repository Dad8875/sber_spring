package ru.dad.service;

import org.springframework.stereotype.Service;
import ru.dad.dto.OrderDTO;
import ru.dad.mapper.OrderMapper;
import ru.dad.model.Order;
import ru.dad.repository.OrderRepository;

import java.time.LocalDate;

@Service
public class OrderService extends GenericService<Order, OrderDTO> {
    protected OrderRepository orderRepository;

    protected OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        super(orderRepository, orderMapper);
        this.orderRepository = orderRepository;
    }
    //новый заказ
    public OrderDTO addOrder(Long userId, Long filmId) {
        OrderDTO orderDTO = new OrderDTO(LocalDate.now(), 10, true, userId, filmId);
        return genericMapper.toDTO(orderRepository.save(genericMapper.toEntity(orderDTO)));
    }
}
