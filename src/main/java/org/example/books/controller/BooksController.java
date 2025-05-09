package org.example.books.controller;

import org.example.books.model.Book;
import org.example.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookService bookService;
    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public List<Book> getAll() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    @PostMapping()
    public void createBook(@RequestBody Book book) {
        bookService.saveBook(book);
    }

    @PatchMapping("/{id}")
    public void updateBook(@PathVariable Long id, @RequestBody Book book) {
        bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
