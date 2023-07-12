package com.example.greatreadsapp.services;

import com.example.greatreadsapp.dtos.BookToAddToDataBaseDTO;
import com.example.greatreadsapp.dtos.BookToAddToListsDTO;
import com.example.greatreadsapp.model.Book;
import com.example.greatreadsapp.model.User;
import com.example.greatreadsapp.repos.BookRepository;
import com.example.greatreadsapp.repos.UserRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@Slf4j
public class BookService {
    @Autowired
    BookRepository _bookRepository;
    @Autowired
    UserRepository _userRepository;

    public ResponseEntity<?> addBooktoBookList(@RequestBody BookToAddToDataBaseDTO bookToAddToDataBaseDTO){
        Book book = new Book(bookToAddToDataBaseDTO.getId(), bookToAddToDataBaseDTO.getTitle(), bookToAddToDataBaseDTO.getDescription(), bookToAddToDataBaseDTO.getAuthor(), bookToAddToDataBaseDTO.getPublishedDate());
        if (_bookRepository.findBooksByTitleAndAuthor(bookToAddToDataBaseDTO.getTitle(), bookToAddToDataBaseDTO.getAuthor()).isPresent() && _bookRepository.findBooksByTitleAndAuthor(bookToAddToDataBaseDTO.getTitle(), bookToAddToDataBaseDTO.getAuthor()).get().equals(book))
            return ResponseEntity.badRequest().body("book is already in database");
        _bookRepository.save(book);
       return ResponseEntity.ok("booksaved");
    }
    @ResponseBody
    public List<Book> allBooks () {
        return _bookRepository.findAll();
    }
    @ResponseBody
    public String addBookToReadedList(BookToAddToListsDTO bookToAddDTO){
        Optional<Book> bookToAdd = _bookRepository.findBooksByTitleAndAuthor(bookToAddDTO.getTitle(),bookToAddDTO.getAuthor());
        Optional<User> user = _userRepository.findBy_email(bookToAddDTO.getUserEmail());
        if(bookToAdd == null)
            return "The mentioned book is not available yet on our website";
        user.get().getReadedBooks().add(bookToAdd.get());
        _userRepository.save(user.get());
        return "Book was added to " + user.get().get_firstName() + "'s readed list. Now your readed books are : " + user.get().getReadedBooks();
    }

    public List<Book> publishedBooks (){
        return _bookRepository.findAll().stream().filter(book -> "PUBLISHED".equals(book.getStatus())).collect(Collectors.toList());
    }






}
