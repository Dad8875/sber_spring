package ru.dad.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.dad.dto.UserDTO;
import ru.dad.model.Order;
import ru.dad.model.User;
import ru.dad.repository.OrderRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper extends GenericMapper<User, UserDTO> {
    private final OrderRepository orderRepository;
    protected UserMapper(ModelMapper modelMapper, OrderRepository orderRepository) {
        super(modelMapper, User.class, UserDTO.class);
        this.orderRepository = orderRepository;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(User.class, UserDTO.class)
                .addMappings(m -> m.skip(UserDTO::setOrdersIds)).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(UserDTO.class, User.class)
                .addMappings(m -> m.skip(User::setUserOrders)).setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(UserDTO source, User destination) {
        if (!Objects.isNull(source.getOrdersIds())) {
            destination.setUserOrders(new HashSet<>(orderRepository.findAllById(source.getOrdersIds())));
        } else {
            destination.setUserOrders(Collections.emptySet());
        }
    }

    @Override
    void mapSpecificFields(User source, UserDTO destination) {
        destination.setOrdersIds(getIds(source));
    }

    @Override
    Set<Long> getIds(User user) {
        return Objects.isNull(user) || Objects.isNull(user.getUserOrders())
                ? null : user.getUserOrders().stream()
                .map(Order::getId).collect(Collectors.toSet());
    }
}
