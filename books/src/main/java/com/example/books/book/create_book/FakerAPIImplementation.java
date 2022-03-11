package com.example.books.book.create_book;

import com.example.books.models.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class FakerAPIImplementation {
    public Book create(){
//        UncheckedObjectMapper objectMapper = new UncheckedObjectMapper();

        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(
                URI.create("https://fakerapi.it/api/v1/books?_quantity=1")
        ).header("accept","application/json").build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(response.body());
        FakerAPiBookResponse fakeBook = null;
        try {
            fakeBook = new ObjectMapper().readValue(response.body(), FakerAPiBookResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(fakeBook.toString());
        FakerAPIBook fBook = fakeBook.getData().get(0);

        Book book = new Book();

        book.setDescription(fBook.getDescription());
        book.setImage(fBook.getImage());
        book.setIsbn(fBook.getIsbn());
        book.setPublished(fBook.getPublished());
        book.setTitle(fBook.getTitle());
        Publisher publisher =  new Publisher();
        publisher.setName(fBook.getPublisher());
        book.setPublisher(publisher);

        List<Genre> genres = new ArrayList<>();
        Genre genre = new Genre();
        genre.setName(fBook.getGenre());
        genres.add(genre);
        book.setGenre(genres);

        List<Author> authors = new ArrayList<>();
        Author author = new Author();
        author.setName(fBook.getAuthor());
        authors.add(author);
        book.setAuthor(authors);

        System.out.println(book.getClass());

        return book;
    }
}

@Getter
@Setter
class FakerAPiBookResponse {
    private String status;
    private int code;
    private int total;
    private ArrayList<FakerAPIBook> data;
}

@Getter
@Setter
class FakerAPIBook{
    @JsonIgnore
    private Long id;
    private String title;
    private String author;
    private String genre;
    private String description;
    private String isbn;
    private String image;
    private Date published;
    private String publisher;
}