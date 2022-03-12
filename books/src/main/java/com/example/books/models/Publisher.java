package com.example.books.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "publishers")
public class Publisher extends BaseModel{
    private String name;
}
