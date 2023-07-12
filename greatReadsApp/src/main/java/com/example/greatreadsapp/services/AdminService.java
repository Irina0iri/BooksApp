package com.example.greatreadsapp.services;

import com.example.greatreadsapp.model.Book;
import com.example.greatreadsapp.repos.BookRepository;
import com.example.greatreadsapp.repos.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class AdminService {

    @Autowired
    BookRepository _bookRepository;
    @Autowired
    UserRepository _userRepository;

    public List<Book> pendingBooks (){
        return _bookRepository.findAll().stream().filter(book -> book.getStatus().equals("pending")).collect(Collectors.toList());
    }
}
