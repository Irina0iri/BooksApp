package com.example.greatreadsapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewPasswordDTO {
    private String email;
    private String password;
    private String newPassword;

}
