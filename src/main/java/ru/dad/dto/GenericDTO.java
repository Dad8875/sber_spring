package ru.dad.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public abstract class GenericDTO {
    private Long id;
    private String createBy;
    private LocalDateTime createWhen;
}
