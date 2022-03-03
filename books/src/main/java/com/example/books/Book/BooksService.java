package com.example.books.Book;

import com.example.books.Author.AuthorService;
import com.example.books.Book.createBook.FakerAPIImplementation;
import com.example.books.Genre.GenreService;
import com.example.books.Publisher.PublisherService;
import com.example.books.models.Author;
import com.example.books.models.Book;
import com.example.books.models.Genre;
import com.example.books.models.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Book> findById(Long BookId){
        return this.booksRepository.findById(BookId);
    }
}
