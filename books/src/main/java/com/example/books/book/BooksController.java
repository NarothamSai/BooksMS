package com.example.books.book;

import com.example.books.book.dto.BookResponse;
import com.example.books.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
