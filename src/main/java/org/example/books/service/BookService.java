package org.example.books.service;

import org.example.books.model.Book;
import org.example.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
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
        List<Book> books = repository.getAllBooks();
        books.remove(id);
        repository.saveBooks(books);
    }

    public void updateBook(int id, Book updateBook) throws IOException {
        List<Book> books = getAllBooks();
        books.set(id, updateBook);
        repository.saveBooks(books);
    }

}
