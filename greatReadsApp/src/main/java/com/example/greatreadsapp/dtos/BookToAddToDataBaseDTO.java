package com.example.greatreadsapp.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class BookToAddToDataBaseDTO {
    private Long id;
    private String author;
    private String title;
    private String description;
    private LocalDate publishedDate;
}
