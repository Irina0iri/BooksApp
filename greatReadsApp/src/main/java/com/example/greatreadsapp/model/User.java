package com.example.greatreadsapp.model;

import com.example.greatreadsapp.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "_email")})
public class User {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @NotEmpty
    private String _firstName;
    @NotNull
    @NotEmpty
    private String _lastName;
    @NotEmpty
    @Email
    private String _email;
    @JsonIgnore
    @ToString.Exclude
    private String _password;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user")
    private List<Review> review;

    @ManyToMany(mappedBy = "users")
    private List<Book> readedBooks;

    @OneToMany(mappedBy = "book_id")
    private Set<Book> wishList;

    public User(Role role, String firstName, String lastName, String email, String password) {
        _firstName = firstName;
        _lastName = lastName;
        _email = email;
        _password = password;
        this.role = role == null ? Role.READER : role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
