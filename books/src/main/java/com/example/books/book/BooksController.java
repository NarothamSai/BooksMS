package com.example.books.book;

import com.example.books.BaseResponse;
import com.example.books.book.dto.BookListResponse;
import com.example.books.book.dto.BookResponse;
import com.example.books.book.dto.UpdateBookRequestBody;
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
}
