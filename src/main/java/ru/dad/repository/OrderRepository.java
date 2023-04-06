package ru.dad.repository;

import org.springframework.stereotype.Repository;
import ru.dad.model.Order;

@Repository
public interface OrderRepository extends GenericRepository<Order> {
}
