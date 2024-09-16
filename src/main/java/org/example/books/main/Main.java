package org.example.books.main;

import org.example.books.config.BookAppConfig;
import org.example.books.controller.BooksController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BookAppConfig.class);
        BooksController controller = context.getBean(BooksController.class);

        controller.start();
    }
}