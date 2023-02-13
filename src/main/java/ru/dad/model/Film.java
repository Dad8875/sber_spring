package ru.dad.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "films")
@Setter
@Getter
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "films_seq", allocationSize = 1)
public class Film extends GenericModel {
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "premier_year", nullable = false)
    private String premierYear;
    @Column(name = "country")
    private String country;
    @Column(name = "genre", nullable = false)
    @Enumerated
    private GenreFilm genre;
    @ManyToMany
    @JoinTable(name = "film_directors", joinColumns = @JoinColumn(name = "film_id"),
            foreignKey = @ForeignKey(name = "FK_FILM_DIRECTORS"),
            inverseJoinColumns = @JoinColumn(name = "director_id"),
            inverseForeignKey = @ForeignKey(name = "FK_DIRECTOR_FILMS"))
    private Set<Director> directors;
}
