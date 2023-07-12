package com.example.greatreadsapp.model;

import com.example.greatreadsapp.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long book_id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "author")
    private String author;
    @Column(name = "publishedDate")
    private LocalDate publishedDate;
    @OneToMany(mappedBy = "book")
    private Set<Review> reviews;
    @Enumerated(EnumType.STRING)
    @Value("pending")
    private Status status = Status.PENDING;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "books_to_readers",joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;


    public Book(Long id,String title, String descrition, String author,LocalDate localDate) {
        this.book_id = id;
        this.title = title;
        this.description = descrition;
        this.author = author;
        this.publishedDate = localDate;
    }
}
