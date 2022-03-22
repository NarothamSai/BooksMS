package com.example.books.book;

import com.example.books.BaseResponse;
import com.example.books.book.dto.*;
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

    @DeleteMapping("/book/{id}")
    public ResponseEntity<BaseResponse> deleteById(@PathVariable Long id){
        BaseResponse baseResponse = this.booksService.deleteById(id);
        return ResponseEntity.status(baseResponse.getStatus()).body(baseResponse);
    }

    @GetMapping("/book")
    public ResponseEntity<BookListResponse> findAll(){
        BookListResponse bookListResponse = this.booksService.findAll();
        return ResponseEntity.status(bookListResponse.getStatus()).body(bookListResponse);
    }

    @PatchMapping("/book/{id}")
    public ResponseEntity<BookResponse> updateById(@PathVariable Long id, @RequestBody UpdateBookRequestBody updateBookRequestBody){
        BookResponse bookResponse = this.booksService.updateById(id,updateBookRequestBody);
        return ResponseEntity.status(bookResponse.getStatus()).body(bookResponse);
    }

    @PatchMapping("/book/{id}/author")
    public ResponseEntity<BookResponse> addAuthorById(@PathVariable Long id, @RequestBody AuthorRequestBody authorRequestBody){
        BookResponse bookResponse = this.booksService.addAuthorById(id, authorRequestBody.getName());
        return ResponseEntity.status(bookResponse.getStatus()).body(bookResponse);
    }

    @DeleteMapping("/book/{id}/author")
    public ResponseEntity<BookResponse> deleteAuthorById(@PathVariable Long id, @RequestBody AuthorRequestBody authorRequestBody){
        BookResponse bookResponse = this.booksService.deleteAuthorById(id, authorRequestBody.getName());
        return ResponseEntity.status(bookResponse.getStatus()).body(bookResponse);
    }

    @PostMapping ("/book/{id}/genre")
    public ResponseEntity<BookResponse> addGenreById(@PathVariable Long id, @RequestBody GenreRequestBody genreRequestBody){
        BookResponse bookResponse = this.booksService.addGenreById(id, genreRequestBody.getName());
        return ResponseEntity.status(bookResponse.getStatus()).body(bookResponse);
    }

    @DeleteMapping ("/book/{id}/genre")
    public ResponseEntity<BookResponse> deleteGenreById(@PathVariable Long id, @RequestBody GenreRequestBody genreRequestBody){
        BookResponse bookResponse = this.booksService.deleteGenreById(id, genreRequestBody.getName());
        return ResponseEntity.status(bookResponse.getStatus()).body(bookResponse);
    }

    @PatchMapping("/book/{id}/publisher")
    public ResponseEntity<BookResponse> updatePublisherById(@PathVariable Long id, @RequestBody PublisherRequestBody publisherRequestBody){
        BookResponse bookResponse = this.booksService.updateBookPublisherById(id, publisherRequestBody.getName());
        return ResponseEntity.status(bookResponse.getStatus()).body(bookResponse);
    }
}
