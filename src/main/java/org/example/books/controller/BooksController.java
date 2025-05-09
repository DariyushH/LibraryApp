package org.example.books.controller;

import org.example.books.dao.BookRepository;
import org.example.books.model.Book;
import org.example.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Component
public class BooksController {

    private final MessageSource messageSource;
    private final BookRepository bookRepository;
    Scanner scanner = new Scanner(System.in);
    Locale locale;

    @Autowired
    public BooksController(BookService bookService, MessageSource messageSource, BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.messageSource =messageSource;
    }


    public void start() throws IOException {
        System.out.println("Choose language (en/ru): ");
        String language = scanner.nextLine();
        if ("ru".equalsIgnoreCase(language)) {
            locale = new Locale("ru", "RU");
        } else {
            locale = new Locale("en", "US");
        }

        while (true) {
            System.out.println(messageSource.getMessage("action.message", null, locale));
            System.out.println(messageSource.getMessage("select1.message", null, locale));
            System.out.println(messageSource.getMessage("select2.message", null, locale));
            System.out.println(messageSource.getMessage("select3.message", null, locale));
            System.out.println(messageSource.getMessage("select4.message", null, locale));


            int select = scanner.nextInt();
            scanner.nextLine();
            switch (select) {
                case 1:
                    getAll();
                    break;
                case 2:
                    createBook();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                default:
                    break;
            }

        }


    }

    public void getAll() throws IOException {
        List<Book> allBooks = bookRepository.getAllBooks();
        allBooks.forEach(System.out::println);

    }

    public void createBook() throws IOException {
        //System.out.println(messageSource.getMessage("bookID.message", null, locale));
        //int id = scanner.nextInt();
        //scanner.nextLine();
        System.out.println(messageSource.getMessage("bookName.message", null, locale));
        String name = scanner.nextLine();

        System.out.println(messageSource.getMessage("bookAuthor.message", null, locale));
        String author = scanner.nextLine();

        System.out.println(messageSource.getMessage("bookDescription.message", null, locale));
        String description = scanner.nextLine();

        Book book = new Book(name, author, description);

        bookRepository.saveBooks(book);
        System.out.println(messageSource.getMessage("successful.message", null, locale));

    }

    public void updateBook() {
        System.out.println(messageSource.getMessage("bookID.message", null, locale));
        Long id = scanner.nextLong();

        System.out.println(messageSource.getMessage("newBookName.message", null, locale));
        String name = scanner.nextLine();

        System.out.println(messageSource.getMessage("newBookAuthor.message", null, locale));
        String author = scanner.nextLine();

        System.out.println(messageSource.getMessage("newBookDescription.message", null, locale));
        String description = scanner.nextLine();

        Book book = new Book(name, author, description);

        bookRepository.update(id, book);
        System.out.println(messageSource.getMessage("successful.message", null, locale));

    }

    public void deleteBook() throws IOException {
        System.out.println(messageSource.getMessage("bookID.message", null, locale));
        Long id = scanner.nextLong();
        bookRepository.deleteBook(id);
        System.out.println(messageSource.getMessage("successful.message", null, locale));
    }


}
