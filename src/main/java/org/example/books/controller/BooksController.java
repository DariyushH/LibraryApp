package org.example.books.controller;

import org.example.books.model.Book;
import org.example.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Component
public class BooksController {
    private final BookService bookService;
    Scanner scanner = new Scanner(System.in);

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }


    public void start() throws IOException {
        while (true) {
            System.out.println("Выбор действия:");
            System.out.println("Нажмите 1 чтобы вывести список книг");
            System.out.println("Нажмите 2 чтобы  создать новую книгу");
            System.out.println("Нажмите 3 отредактировать книгу");
            System.out.println("Нажмите 4 удалить книгу");


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
        List<Book> allBooks = bookService.getAllBooks();
        allBooks.forEach(System.out::println);

    }

    public void createBook() throws IOException {
        System.out.println("Введите id книги ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введите название книги ");
        String name = scanner.nextLine();

        System.out.println("Введите автора книги ");
        String author = scanner.nextLine();

        System.out.println("Введите описание книги ");
        String description = scanner.nextLine();

        Book book = new Book(id, name, author, description);

        bookService.addBook(book);
        System.out.println("Книга успешно добавлена ");

    }

    public void updateBook() throws IOException {
        System.out.println("Введите id книги ");
        int id = scanner.nextInt();

        System.out.println("Введите новое название книги ");
        String name = scanner.nextLine();

        System.out.println("Введите нового автора книги ");
        String author = scanner.nextLine();

        System.out.println("Введите новое описание книги ");
        String description = scanner.nextLine();

        Book book = new Book(id, name, author, description);

        bookService.updateBook(id, book);
        System.out.println("Книга успешно обновлена ");
    }

    public void deleteBook() throws IOException {
        System.out.println("Введите id книги которую хотите удалить ");
        int id = scanner.nextInt();
        bookService.deleteBook(id);
        System.out.println("Книга успешно удалена ");
    }
}
