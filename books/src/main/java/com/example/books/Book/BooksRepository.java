package com.example.books.Book;

import com.example.books.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book,Long> {
    Book save(Book book);

    Book findByTitle(String title);

    @Override
    Optional<Book> findById(Long aLong);
}
