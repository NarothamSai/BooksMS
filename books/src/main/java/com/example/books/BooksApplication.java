package com.example.books;

import com.example.books.Book.BooksService;
import com.example.books.Book.createBook.FakerAPIImplementation;
import com.example.books.models.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BooksApplication {

  public static void main(String[] args) {
    SpringApplication.run(BooksApplication.class, args);

//    Book test = new BooksService().create();
//
//    System.out.println(test);
  }
}
