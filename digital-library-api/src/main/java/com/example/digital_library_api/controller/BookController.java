package com.example.digital_library_api.controller;

import com.example.digital_library_api.entity.Book;
import com.example.digital_library_api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Получить все книги
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Получить книгу по ISBN
    @GetMapping("/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) {
        return bookRepository.findById(isbn)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    // Добавить книгу (POST с JSON)
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }
}