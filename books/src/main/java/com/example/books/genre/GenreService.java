package com.example.books.genre;

import com.example.books.models.Genre;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;


    public Genre create(String name){
        Genre genre = this.genreRepository.findByName(name);

        if(genre == null){
            Genre newGenre = new Genre();
            newGenre.setName(name);
            genre = this.genreRepository.save(newGenre);
        }

        return genre;
    }
}
