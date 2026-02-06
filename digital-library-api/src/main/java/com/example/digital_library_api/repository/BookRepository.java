package com.example.digital_library_api.repository;

import org.springframework.data.repository.query.Param;
import com.example.digital_library_api.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    // методы
    // findAll()
    // findById(String isbn)
    // save(Book book)
    // deleteById(String isbn)
    // delete(Book book)
    // count()
    // existsById(String isbn)

    // свои методы

    // поиск по точному совпадению автора
    List<Book> findByAuthor(String author);

    // по части названия игнорируя регистр
    List<Book> findByTitleContainingIgnoreCase(String titlePart);

    // все доступные книги
    List<Book> findByAvailableTrue();

    // опред жанр по году
    List<Book> findByGenreOrderByPublicationYearDesc(String genre);

    // 5. Если нужен сложный запрос — пишем JPQL или native SQL
    @Query("SELECT b FROM Book b WHERE b.genre = :genre AND b.available = true")
    List<Book> findAvailableBooksByGenre(@Param("genre") String genre);

    // или native SQL (если очень нужно)
    @Query(value = "SELECT * FROM books WHERE publication_year > :year", nativeQuery = true)
    List<Book> findBooksNewerThan(@Param("year") int year);
}