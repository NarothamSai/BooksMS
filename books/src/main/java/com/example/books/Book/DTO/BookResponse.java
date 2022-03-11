package com.example.books.Book.DTO;

import com.example.books.BaseResponse;
import com.example.books.models.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponse extends BaseResponse {
    private Book book;
}
