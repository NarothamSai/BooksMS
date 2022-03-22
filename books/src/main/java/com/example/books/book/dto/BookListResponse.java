package com.example.books.book.dto;

import com.example.books.BaseResponse;
import com.example.books.models.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookListResponse extends BaseResponse {
    List<Book> books;
}
