package com.example.greatreadsapp.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookToAddToListsDTO {
    private String author;
    private String title;
    private String userEmail;
}
