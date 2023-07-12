package com.example.greatreadsapp.repos;

import com.example.greatreadsapp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<List<Book>> findAllByAuthor (String authorName);
    Optional<Book> findBooksByDescription(String type);
    Optional<Book> findBooksByTitle(String title);

    Optional<Book> findBooksByTitleAndAuthor(String title,String author);

    List<Book> findAll ();

}
