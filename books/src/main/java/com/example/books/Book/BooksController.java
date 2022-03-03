package com.example.books.Book;

import com.example.books.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.books.Book.BooksService;

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
    public Optional<Book> updateById(@PathVariable Long id) {
        return this.booksService.findById(id);
    }

}
