package com.example.greatreadsapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "reviews")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long review_id;
    @Column
    private String description;
    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id",insertable=false, updatable=false)
    private Book book;
    @ManyToOne(optional = false)
    @JoinColumn(name = "review_id",insertable=false, updatable=false)
    private User user;

}
