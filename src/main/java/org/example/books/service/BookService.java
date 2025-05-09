package org.example.books.service;

import org.example.books.model.Book;
import org.example.books.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Book getBook(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void updateBook(Long id, Book book) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book bookToBeUpdated = optionalBook.get();
            bookToBeUpdated.setName(bookToBeUpdated.getName());
            bookToBeUpdated.setAuthor(bookToBeUpdated.getAuthor());
            bookToBeUpdated.setDescription(bookToBeUpdated.getDescription());

            bookRepository.save(bookToBeUpdated);
        }
    }
}
