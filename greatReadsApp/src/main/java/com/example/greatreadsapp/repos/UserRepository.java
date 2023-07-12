package com.example.greatreadsapp.repos;

import com.example.greatreadsapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBy_email(String email);
    Boolean existsBy_email(String email);


}
