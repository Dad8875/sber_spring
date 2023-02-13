package ru.dad.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "directors")
@SequenceGenerator(name = "default_generator", sequenceName = "directors_seq", allocationSize = 1)
@NoArgsConstructor
@Getter
@Setter
public class Director extends GenericModel {
    @Column(name = "directions_fio", nullable = false)
    private String fio;
    @Column(name = "position")
    private String position;
    @ManyToMany(mappedBy = "directors")
    private Set<Film> films;
}
