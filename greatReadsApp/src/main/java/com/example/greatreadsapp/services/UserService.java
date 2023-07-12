package com.example.greatreadsapp.services;

import com.example.greatreadsapp.*;
import com.example.greatreadsapp.dtos.LoginRequestDTO;
import com.example.greatreadsapp.dtos.LoginResponseDTO;
import com.example.greatreadsapp.dtos.NewPasswordDTO;
import com.example.greatreadsapp.dtos.RegisterRequestDTO;
import com.example.greatreadsapp.model.User;
import com.example.greatreadsapp.repos.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
@Service
public class UserService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JWTService jwtHelper;

    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImplementation userDetails = (UserDetailsImplementation) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtHelper.generateJwtCookie(userDetails);

        String role = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()).get(0);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new LoginResponseDTO(userDetails.getEmail(), jwtCookie.getValue(), userDetails.getId(), role));}

    @Transactional
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        if (userRepository.existsBy_email(registerRequestDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Email used");
        }
        String role = registerRequestDTO.getRole();

        if (role == null || !(role.toUpperCase(Locale.ROOT).equals("ADMIN") || role.toUpperCase(Locale.ROOT).equals("AUTHOR") || role.toUpperCase(Locale.ROOT).equals("READER"))) {
            throw new RuntimeException("Invalid role");
        }

        User user = new User(Role.valueOf(registerRequestDTO.getRole()),registerRequestDTO.getFirstName(), registerRequestDTO.getLastName(), registerRequestDTO.getEmail(), encoder.encode(registerRequestDTO.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    public ResponseEntity<?> changePassword (NewPasswordDTO newPasswordDTO){
        Optional<User> userfound = userRepository.findBy_email(newPasswordDTO.getEmail());
        if(userfound != null && userfound.get().get_password().equals(newPasswordDTO.getPassword())){
            userfound.get().set_password(newPasswordDTO.getNewPassword());
            userRepository.save(userfound.get());
            return ResponseEntity.ok("User password changed successfully!");
        }else{
            return ResponseEntity.badRequest().body("User does not exist in the system or the old password is not correct");
        }
    }
}
