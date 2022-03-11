package com.example.books.author;

import com.example.books.models.Author;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Author create(String name){
        Author author =  this.authorRepository.findByName(name);

        if(author == null){
            Author newAuthor = new Author();
            newAuthor.setName(name);
            author = this.authorRepository.save(newAuthor);
        }


        return author;
    }
}
