package com.example.books.Author;

import com.example.books.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    Author save(Author author);

    Author findByName(String name);
}
