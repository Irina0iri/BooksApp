package com.example.greatreadsapp.services;

import com.example.greatreadsapp.model.Book;
import com.example.greatreadsapp.model.Review;
import com.example.greatreadsapp.model.User;
import com.example.greatreadsapp.repos.BookRepository;
import com.example.greatreadsapp.repos.ReviewRepository;
import com.example.greatreadsapp.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;
@Service
public class ReviewService {
    @Autowired
    private ReviewRepository _reviewRepository;
    @Autowired
    private BookRepository _bookRepository;
    @Autowired
    private UserRepository _userRepository;


    @ResponseBody String addReview(Long userId,Long bookId,String review){
        Optional<Book> book = _bookRepository.findById(bookId);
        if(book.get().getStatus().equals("published")) {
            Optional<User> user = _userRepository.findById(userId);
            Review review1 = new Review(null, review, book.get(), user.get());
            book.get().getReviews().add(review1);
            return "review added succesfully";
        }else
            return "book was not published yet";
    }

    @ResponseBody String removeReview(Book book,Review review){
        Optional<Book> bookOptional = _bookRepository.findBooksByTitle(book.getTitle());
        if(review.getUser().equals(bookOptional.get()))
        bookOptional.get().getReviews().remove(review);
        return "review removed";
    }


}
