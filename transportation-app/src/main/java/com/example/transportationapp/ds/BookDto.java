package com.example.transportationapp.ds;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BookDto {
    private String title;
    private String authorName;
    private double price;
    private String publisher;
    private LocalDate yearPublished;
    private String categoryName;
    private String genre;
    private String imageUrl;
    private Integer itemCount;

    public BookDto() {
    }

    public BookDto(String title, String authorName, double price, String publisher, LocalDate yearPublished, String genre, String imageUrl, Integer itemCount) {
        this.title = title;
        this.authorName = authorName;
        this.price = price;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        this.genre = genre;
        this.imageUrl = imageUrl;
        this.itemCount=itemCount;
    }

}
