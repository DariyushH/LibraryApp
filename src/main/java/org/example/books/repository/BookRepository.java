package org.example.books.repository;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.example.books.model.Book;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    CsvMapper csvMapper = new CsvMapper();
    CsvSchema schema = csvMapper.schemaFor(Book.class).withHeader();
    File csvFile = new File("src/main/resources/books.csv");


    public List<Book> getAllBooks() throws IOException {

        if (!csvFile.exists()) {
            return new ArrayList<>();
        }
        MappingIterator<Book> books = csvMapper.readerFor(Book.class).with(schema).readValues(csvFile);
        return books.readAll();
    }

    public void saveBooks(List<Book> books) throws IOException {
        csvMapper.writerFor(Book.class).with(schema).writeValues(csvFile).writeAll(books);
    }
}

