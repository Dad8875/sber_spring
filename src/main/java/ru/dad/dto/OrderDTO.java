package ru.dad.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO extends GenericDTO {
    private LocalDate rentDate;
    private Integer rentPeriod;
    private Boolean purchase;

    private Long userId;
    private Long filmId;
}
