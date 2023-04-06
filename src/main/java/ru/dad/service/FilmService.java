package ru.dad.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.dad.dto.FilmDTO;
import ru.dad.mapper.FilmMapper;
import ru.dad.model.Film;
import ru.dad.model.Order;
import ru.dad.repository.FilmRepository;
import ru.dad.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FilmService extends GenericService<Film, FilmDTO> {
    protected final FilmRepository filmRepository;
    protected final OrderRepository orderRepository;

    protected FilmService(FilmRepository filmRepository,
                          FilmMapper filmMapper,
                          OrderRepository orderRepository) {
        super(filmRepository, filmMapper);
        this.filmRepository = filmRepository;
        this.orderRepository = orderRepository;
    }

    //добавить режиссера фильму
    public FilmDTO addDirector(Long filmId, Long directorId) {
        FilmDTO filmDTO = genericMapper.toDTO(filmRepository
                .findById(filmId).orElseThrow(() -> new NotFoundException("Нет данных по переданному ID " + filmId)));
        filmDTO.getDirectorsIds().add(directorId);
        return genericMapper.toDTO(filmRepository.save(genericMapper.toEntity(filmDTO)));
    }

    //все заказы пользователя
    public List<FilmDTO> getAllOrders(Long userId) {
        List<FilmDTO> filmDTOS = new ArrayList<>();
        List<Order> orders = orderRepository.findAll();
        for (Order order : orders) {
            if (Objects.equals(order.getUser().getId(), userId)) {
                filmDTOS.add(genericMapper.toDTO(order.getFilm()));
            }
        }
        return filmDTOS;
    }
}
