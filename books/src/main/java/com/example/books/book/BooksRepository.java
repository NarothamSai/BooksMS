package com.example.books.book;

import com.example.books.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Book,Long> {
    Book save(Book book);

    Book findByTitle(String title);
}
