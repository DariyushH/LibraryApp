package org.example.books.service.implementation;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.example.books.model.Book;
import org.example.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {
    private final GridFsTemplate gridFsTemplate;
    private final BookRepository bookRepository;

    @Autowired
    public ImageService(GridFsTemplate gridFsTemplate, BookRepository bookRepository) {
        this.gridFsTemplate = gridFsTemplate;
        this.bookRepository = bookRepository;
    }

    public String uploadImage(MultipartFile file, Long bookId) throws IOException {
        Book book = bookRepository.findById(bookId).orElseThrow();
        String fileId = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename()).toString();
        book.setFileId(fileId);
        bookRepository.save(book);
        return fileId;
    }

    public GridFsResource downloadImage(String fileId) {
        GridFSFile file = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fileId)));
        return gridFsTemplate.getResource(file);
    }
}
