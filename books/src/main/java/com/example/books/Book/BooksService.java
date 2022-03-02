package com.example.books.Book;

import com.example.books.Book.createBook.FakerAPIImplementation;
import com.example.books.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {
    @Autowired
    private BooksRepository booksRepository;

    public Book create(){
        Book book = new FakerAPIImplementation().create();

       Book createdBook = this.booksRepository.save(book);

       return createdBook;
    }
}
