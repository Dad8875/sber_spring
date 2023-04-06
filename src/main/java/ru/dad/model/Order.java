package ru.dad.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Setter
@Getter
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "orders_seq", allocationSize = 1)
public class Order extends GenericModel {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_ORDER_USER"))
    private User user;
    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false, foreignKey = @ForeignKey(name = "FK_ORDER_FILM"))
    private Film film;
    @Column(name = "rent_date", nullable = false)
    private LocalDate rentDate;
    @Column(name = "rent_period", nullable = false)
    private Integer rentPeriod;
    @Column(name = "purchase", nullable = false)
    private Boolean purchase;
}
