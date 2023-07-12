package com.example.greatreadsapp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    PENDING("pending"),
    PUBLISHED("published");

    private final String status;
}
