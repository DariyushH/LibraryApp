package org.example.books.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.example.books.model.Book;
import org.example.books.repository.BookRepository;
import org.example.books.service.implementation.BookService;
import org.example.books.service.implementation.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;


@RestController
@RequestMapping("/books")
public class BooksController {
    private final BookService bookService;
    private final ImageService imageService;

    private final BookRepository bookRepository;
    @Autowired
    public BooksController(BookService bookService, ImageService imageService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.imageService = imageService;
        this.bookRepository = bookRepository;
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

    @PostMapping("/{id}/upload-image")
    public void uploadImage(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) throws IOException {
        imageService.uploadImage(file, id);
    }

    @GetMapping("/{id}/download-image")
    public void downloadImage(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        String fileId = bookRepository.findById(id).orElseThrow().getFileId();
        GridFsResource resource = imageService.downloadImage(fileId);

        String filename = URLEncoder.encode(resource.getFilename(), "UTF-8");

        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + filename);
        response.setContentType("application/octet-stream");

        try (InputStream inputStream = resource.getInputStream()) {
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole(T(org.example.books.enums.UserRole).ROLE_ADMIN)")
    public String admin() {
        return "ADMIN PAGE";
    }
}



