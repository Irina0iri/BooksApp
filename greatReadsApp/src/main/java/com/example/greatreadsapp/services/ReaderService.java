package com.example.greatreadsapp.services;

import com.example.greatreadsapp.model.Book;
import com.example.greatreadsapp.model.User;
import com.example.greatreadsapp.repos.BookRepository;
import com.example.greatreadsapp.repos.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Service
@Data
public class ReaderService {

    @Autowired
    BookRepository _bookRepository;
    @Autowired
    UserRepository _userRepository;

    public boolean readedBook (User user, Book book){
        return user.getReadedBooks().contains(book);
    }

    public @ResponseBody String addToWishList(String bookTitle,String email){
        Optional<Book> bookOptional = _bookRepository.findBooksByTitle(bookTitle);
        Optional<User> user = _userRepository.findBy_email(email);
        if(user.isEmpty() || bookOptional.isEmpty())
            return "Book could not be added to wishList";
        user.get().getWishList().add(bookOptional.get());
        _userRepository.save(user.get());
        return "book added successfully";
    }

}
