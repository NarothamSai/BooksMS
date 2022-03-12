package com.example.books.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "books")
public class Book extends BaseModel{
    private String title;
    private String description;

    @ManyToMany
    private List<Author> author;

    @ManyToMany
    private List<Genre> genre;

    @ManyToOne
    private Publisher publisher;

    private Date published;
    private String image;
    private String isbn;
}
