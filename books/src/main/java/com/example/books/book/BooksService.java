package com.example.books.book;

import com.example.books.BaseResponse;
import com.example.books.author.AuthorService;
import com.example.books.book.dto.BookListResponse;
import com.example.books.book.dto.BookResponse;
import com.example.books.book.create_book.FakerAPIImplementation;
import com.example.books.book.dto.UpdateBookRequestBody;
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

    public BookResponse findById(Long BookId){
        Optional<Book> book = this.booksRepository.findById(BookId);
        BookResponse bookResponse = new BookResponse();

        if(book.isEmpty()){
            bookResponse.setMessage("Book Not found.");
            bookResponse.setStatus(404);
        }else {
            bookResponse.setMessage("Book found.");
            bookResponse.setStatus(200);
            bookResponse.setBook(book.get());
        }

        return bookResponse;
    }

    public BaseResponse deleteById(Long bookId){
        BookResponse bookResponse = findById(bookId);
        BaseResponse baseResponse = new BaseResponse();

        if(bookResponse.getStatus() == 404){
            baseResponse.setMessage("Book Not Found.");
            baseResponse.setStatus(404);
        }else {
            this.booksRepository.deleteById(bookId);
            baseResponse.setMessage("Book Deleted.");
            baseResponse.setStatus(200);
        }

        return baseResponse;
    }

    public BookListResponse findAll(){
        BookListResponse bookListResponse = new BookListResponse();

        bookListResponse.setBooks(this.booksRepository.findAll());
        bookListResponse.setStatus(200);
        bookListResponse.setMessage("Books found.");

        return  bookListResponse;
    }

    public BookResponse updateById(Long bookId, UpdateBookRequestBody updateBookRequestBody){
        BookResponse bookResponse = new BookResponse();

        BookResponse findBookResponse = findById(bookId);

        if(findBookResponse.getStatus() == 404){
            bookResponse.setStatus(404);
            bookResponse.setMessage("Book Not Found.");
        }else{
            Book book = findBookResponse.getBook();
            if(updateBookRequestBody.getDescription() != null) book.setDescription(updateBookRequestBody.getDescription());
            if(updateBookRequestBody.getImage() != null) book.setImage(updateBookRequestBody.getImage());
            if(updateBookRequestBody.getIsbn() != null) book.setIsbn(updateBookRequestBody.getIsbn());
            if(updateBookRequestBody.getPublished() != null) book.setPublished(updateBookRequestBody.getPublished());
            if(updateBookRequestBody.getTitle() != null) book.setTitle(updateBookRequestBody.getTitle());

            Book savedBook = this.booksRepository.save(book);
            bookResponse.setStatus(204);
            bookResponse.setMessage("Book updated.");
            bookResponse.setBook(savedBook);
        }

        return bookResponse;
    }

    public BookResponse addAuthorById(Long bookId, String authorName){
        BookResponse bookResponse = new BookResponse();

        BookResponse findBookResponse = findById(bookId);

        if(findBookResponse.getStatus() == 404){
            bookResponse.setStatus(404);
            bookResponse.setMessage("Book Not Found.");
        }else{
            Book book = findBookResponse.getBook();

            Author author = this.authorService.upsert(authorName);

            List<Author> authors = book.getAuthor();
            authors.add(author);

            book.setAuthor(authors);

            Book savedBook = this.booksRepository.save(book);

            bookResponse.setStatus(204);
            bookResponse.setMessage("Book updated.");
            bookResponse.setBook(savedBook);
        }

        return bookResponse;
    }

    public BookResponse deleteAuthorById(Long bookId, String authorName){
        BookResponse bookResponse = new BookResponse();

        BookResponse findBookResponse = findById(bookId);

        if(findBookResponse.getStatus() == 404){
            bookResponse.setStatus(404);
            bookResponse.setMessage("Book Not Found.");
        }else{
            Book book = findBookResponse.getBook();

            Author author = this.authorService.findByName(authorName);

            if(author == null){
                bookResponse.setStatus(404);
                bookResponse.setMessage("Author Not Found.");
            }else{
                List<Author> authors = book.getAuthor();
                authors.remove(author);

                book.setAuthor(authors);

                Book savedBook = this.booksRepository.save(book);

                bookResponse.setStatus(204);
                bookResponse.setMessage("Book updated.");
                bookResponse.setBook(savedBook);
            }

        }

        return bookResponse;
    }

    public BookResponse addGenreById(Long bookId, String genreName){
        BookResponse bookResponse = new BookResponse();

        BookResponse findBookResponse = findById(bookId);

        if(findBookResponse.getStatus() == 404){
            bookResponse.setStatus(404);
            bookResponse.setMessage("Book Not Found.");
        }else{
            Book book = findBookResponse.getBook();

            Genre genre = this.genreService.upsert(genreName);

            List<Genre> genres = book.getGenre();
            if(genres.contains(genre) == true){
                bookResponse.setStatus(409);
                bookResponse.setMessage("Genre already exists for the book.");
                bookResponse.setBook(book);
            }else{
                genres.add(genre);

                book.setGenre(genres);

                Book savedBook = this.booksRepository.save(book);

                bookResponse.setStatus(204);
                bookResponse.setMessage("Book updated.");
                bookResponse.setBook(savedBook);
            }

        }

        return bookResponse;
    }

    public BookResponse deleteGenreById(Long bookId, String genreName){
        BookResponse bookResponse = new BookResponse();

        BookResponse findBookResponse = findById(bookId);

        if(findBookResponse.getStatus() == 404){
            bookResponse.setStatus(404);
            bookResponse.setMessage("Book Not Found.");
        }else{
            Book book = findBookResponse.getBook();

            Genre genre = this.genreService.findByName(genreName);

            if(genre == null){
                bookResponse.setStatus(404);
                bookResponse.setMessage("Genre Not Found.");
            }else{
                List<Genre> genres = book.getGenre();

                if(genres.contains(genre) == true){
                    genres.remove(genre);

                    book.setGenre(genres);

                    Book savedBook = this.booksRepository.save(book);

                    bookResponse.setStatus(204);
                    bookResponse.setMessage("Book updated.");
                    bookResponse.setBook(savedBook);
                }else{
                    bookResponse.setStatus(409);
                    bookResponse.setMessage("Genre does not exists in book.");
                    bookResponse.setBook(book);
                }

            }

        }

        return bookResponse;
    }

    public BookResponse updateBookPublisherById(Long bookId, String publisherName){
        BookResponse bookResponse = new BookResponse();

        BookResponse findBookResponse = findById(bookId);

        if(findBookResponse.getStatus() == 404){
            bookResponse.setStatus(404);
            bookResponse.setMessage("Book Not Found.");
        }else{
            Book book = findBookResponse.getBook();

            book.setPublisher(this.publisherService.upsert(publisherName));

            this.booksRepository.save(book);

            bookResponse.setStatus(204);
            bookResponse.setMessage("Book updated.");
        }

        return bookResponse;
    }
}
