package com.example.digital_library_api.service;

import com.example.digital_library_api.entity.Book;
import com.example.digital_library_api.entity.LibraryUser;
import com.example.digital_library_api.repository.BookRepository;
import com.example.digital_library_api.repository.LibraryUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor   // вместо @Autowired
public class LibraryService {

    private final BookRepository bookRepository;
    private final LibraryUserRepository userRepository;

    // ── Book operations ───────────────────────────────────────

    @Transactional
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> findBookByIsbn(String isbn) {
        return bookRepository.findById(isbn);
    }

    public List<Book> findBooksByTitleContains(String fragment) {
        return bookRepository.findByTitleContainingIgnoreCase(fragment);
    }

    public List<Book> findBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getAllAvailableBooks() {
        return bookRepository.findByAvailableTrue()
                .stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());
    }

    public List<Book> getTopNewestBooks(int limit) {
        return bookRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Book::getPublicationYear).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    // ── User operations ───────────────────────────────────────

    @Transactional
    public LibraryUser registerUser(LibraryUser user) {
        if (userRepository.existsById(user.getId())) {
            throw new IllegalArgumentException("User with id " + user.getId() + " already exists");
        }
        return userRepository.save(user);
    }

    public Optional<LibraryUser> findUser(String id) {
        return userRepository.findById(id);
    }

    // можно добавить borrow/return логику сюда
    @Transactional
    public void borrowBook(String userId, String isbn) {
        LibraryUser user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        book.borrow();               // бизнес-логика
        bookRepository.save(book);   // сохраняем изменение
        // можно добавить связь user ↔ borrowed books, если нужно
    }
}