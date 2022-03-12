package com.example.books.book;

import com.example.books.models.Book;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book,Long> {
    Book save(Book book);

    Book findByTitle(String title);

    @Override
    Optional<Book> findById(Long aLong);

    @Override
    List<Book> findAll();

    @Override
    <S extends Book> Page<S> findAll(Example<S> example, Pageable pageable);
}
