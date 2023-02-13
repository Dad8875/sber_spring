package ru.dad.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@MappedSuperclass
@Getter
@NoArgsConstructor
public class GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_generator")
    @Column(name = "id")
    private Long id;
}



