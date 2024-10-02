package org.example.books.service;

import org.example.books.dao.BookRepository;
import org.example.books.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BookService {
    private final JdbcTemplate jdbcTemplate;
    private final BookRepository repository;

    @Autowired
    public BookService(JdbcTemplate jdbcTemplate, BookRepository repository) {
        this.jdbcTemplate = jdbcTemplate;
        this.repository = repository;
    }

    public List<Book> getAllBooks() throws IOException {
        return repository.getAllBooks();
    }

    public void addBook(Book book) throws IOException {
        List<Book> books = repository.getAllBooks();
        books.add(book);
        repository.saveBooks(books);

    }

    public void deleteBook(int id) throws IOException {
        String sql = "DELETE FROM books WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void updateBook(int id, Book updateBook) throws IOException {

        String sql = "UPDATE books SET name = ?, author = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(sql, updateBook.getName(), updateBook.getAuthor(), updateBook.getDescription(), id);

    }
}
