package com.example.books.genre;

import com.example.books.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {
    Genre save(Genre genre);

    Genre findByName(String name);
}
