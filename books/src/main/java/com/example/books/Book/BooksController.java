package com.example.books.Book;

import com.example.books.Book.DTO.BookResponse;
import com.example.books.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.books.Book.BooksService;

import java.net.http.HttpResponse;
import java.util.Optional;

@RestController
public class BooksController {
    @Autowired
    private BooksService booksService;

    @PostMapping("/book")
    public Book create(){
        return booksService.create();
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookResponse> updateById(@PathVariable Long id) {
        BookResponse bookResponse = this.booksService.findById(id);
        return ResponseEntity.status(bookResponse.getStatus()).body(bookResponse);
    }

}
