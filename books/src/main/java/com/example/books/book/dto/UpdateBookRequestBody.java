package com.example.books.book.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class UpdateBookRequestBody {
    private String title;
    private String description;
    private Date published;
    private String image;
    private String isbn;
}
