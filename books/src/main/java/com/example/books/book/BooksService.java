package com.example.books.book;

import com.example.books.author.AuthorService;
import com.example.books.book.create_book.FakerAPIImplementation;
import com.example.books.genre.GenreService;
import com.example.books.publisher.PublisherService;
import com.example.books.models.Author;
import com.example.books.models.Book;
import com.example.books.models.Genre;
import com.example.books.models.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BooksService {
    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private GenreService genreService;

    public Book create(){
        Book book = new FakerAPIImplementation().create();

        Book alreadyExistsBook = this.booksRepository.findByTitle(book.getTitle());

        if(alreadyExistsBook != null){
            return alreadyExistsBook;
        }

        Publisher publisher = this.publisherService.create(book.getPublisher().getName());
        book.setPublisher(publisher);

        List<Author> authors = new ArrayList<>();
        for(Author author : book.getAuthor()){
            authors.add(this.authorService.create(author.getName()));
        }
        book.setAuthor(authors);

        List<Genre> genres = new ArrayList<>();
        for(Genre genre : book.getGenre()){
            genres.add(this.genreService.create(genre.getName()));
        }
        book.setGenre(genres);

       Book createdBook = this.booksRepository.save(book);

       return createdBook;
    }
}
