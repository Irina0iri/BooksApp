package com.example.greatreadsapp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@AllArgsConstructor
public enum Role {
    READER("reader"),
    ADMIN("admin"),
    AUTHOR("author");
    private final String role;
}
