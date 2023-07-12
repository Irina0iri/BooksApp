package com.example.greatreadsapp.controlers;

import com.example.greatreadsapp.dtos.BookToAddToListsDTO;
import com.example.greatreadsapp.model.Book;
import com.example.greatreadsapp.model.User;
import com.example.greatreadsapp.repos.BookRepository;
import com.example.greatreadsapp.repos.UserRepository;
import com.example.greatreadsapp.services.BookService;
import com.example.greatreadsapp.services.ReaderService;
import com.example.greatreadsapp.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reader")
@CrossOrigin(origins="*",maxAge = 3600)
public class ReaderController {
    @Autowired
    BookService bookService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    ReaderService readerService;

    @GetMapping("/allBooks")
    public List<Book> books (){
        return bookService.allBooks();
    }
    @PostMapping("/wishListAdd")
    public @ResponseBody String addToWishList (@RequestParam (name = "email") String email,@RequestParam(name = "title") String bookTitle){
        return readerService.addToWishList(bookTitle,email);
    }
    @PostMapping("/addReadedBooks")

    public @ResponseBody String addBookToReadedList (@RequestBody BookToAddToListsDTO bookToAddDTO){
        return bookService.addBookToReadedList(bookToAddDTO);
    }

}
