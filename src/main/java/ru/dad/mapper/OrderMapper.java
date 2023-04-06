package ru.dad.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;
import ru.dad.dto.OrderDTO;
import ru.dad.model.Order;
import ru.dad.repository.FilmRepository;
import ru.dad.repository.UserRepository;

import java.util.Set;

@Component
public class OrderMapper extends GenericMapper<Order, OrderDTO> {

    private final UserRepository userRepository;
    private final FilmRepository filmRepository;
    protected OrderMapper(ModelMapper modelMapper,
                          UserRepository userRepository,
                          FilmRepository filmRepository) {
        super(modelMapper, Order.class, OrderDTO.class);
        this.userRepository = userRepository;
        this.filmRepository = filmRepository;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Order.class, OrderDTO.class)
                .addMappings(m -> m.skip(OrderDTO::setFilmId)).setPostConverter(toDTOConverter())
                .addMappings(m -> m.skip(OrderDTO::setUserId)).setPostConverter(toDTOConverter());

        modelMapper.createTypeMap(OrderDTO.class, Order.class)
                .addMappings(m -> m.skip(Order::setFilm)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Order::setUser)).setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(OrderDTO source, Order destination) {
        destination.setFilm(filmRepository.findById(source.getFilmId())
                .orElseThrow(() -> new NotFoundException("Нет записи по переданному ID " + source.getFilmId())));
        destination.setUser(userRepository.findById(source.getUserId())
                .orElseThrow(() -> new NotFoundException("Нет записи по переданному ID " + source.getUserId())));
    }

    @Override
    void mapSpecificFields(Order source, OrderDTO destination) {
        destination.setFilmId(source.getFilm().getId());
        destination.setUserId(source.getUser().getId());
    }

    @Override
    Set<Long> getIds(Order order) {
        throw new UnsupportedOperationException("Метод недоступен");
    }
}
