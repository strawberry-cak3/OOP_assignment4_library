package com.example.digital_library_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Year;

@Entity
@Table(name = "books")
@Data
public class Book {

    @Id
    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "publication_year", nullable = false)
    private Year publicationYear;        // или просто private int publicationYear;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "available", nullable = false)
    private boolean available = true;

    // Обязательный пустой конструктор для JPA
    public Book() {
    }

    // Удобный конструктор для создания новых книг
    public Book(String isbn, String title, String author, Year publicationYear, String genre) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genre = genre != null ? genre : "Unknown";
    }

    // Бизнес-логика
    public void borrow() {
        if (!available) {
            throw new IllegalStateException("Book is already borrowed: " + title);
        }
        this.available = false;
    }

    public void returnBook() {
        this.available = true;
    }
}