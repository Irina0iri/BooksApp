package com.example.greatreadsapp.controlers;

import com.example.greatreadsapp.dtos.BookToAddToDataBaseDTO;
import com.example.greatreadsapp.model.Book;
import com.example.greatreadsapp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/author")
public class AuthorControler {
    @Autowired
    private BookService bookService;
    @GetMapping(path = "/allBooks/{authorName}")
    public List<Book> viewAllBooks (@PathVariable String authorName){
        return bookService.get_bookRepository().findAll().stream().filter(book -> authorName.equals(book.getAuthor())).collect(Collectors.toList());
    }

    @GetMapping(path = "/allBooks/{bookType}")
    public List<Book> viewAllBooksOfTheSameKind (@PathVariable String bookType){
        return bookService.get_bookRepository().findAll().stream().filter(book -> bookType.equals(book.getDescription())).collect(Collectors.toList());
    }

    @PostMapping("/addBook")
    public ResponseEntity<?> addBook (@RequestBody BookToAddToDataBaseDTO book){
        return bookService.addBooktoBookList(book);
    }

    @GetMapping("/publishedBooks")
    public List<Book> publishedBooks (){
        return bookService.publishedBooks();
    }

    @GetMapping("/allBooks")
    public List<Book> books (){
        return bookService.allBooks();
    }


}
