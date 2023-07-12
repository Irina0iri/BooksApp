package com.example.greatreadsapp.controlers;

import com.example.greatreadsapp.dtos.*;
import com.example.greatreadsapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins="*",maxAge = 3600)
@RestController
@RequestMapping("/user")
public class RegisterControler {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return userService.register(registerRequestDTO);
    }

    @PostMapping("/newPassword")
    public ResponseEntity<?> changePassword(@RequestBody NewPasswordDTO newPasswordDTO) {
        return userService.changePassword(newPasswordDTO);
    }

}
