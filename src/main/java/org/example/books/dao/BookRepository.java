package org.example.books.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import org.example.books.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Repository
public class BookRepository {

    private final SessionFactory sessionFactory;
    @Autowired
    public BookRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Book> getAllBooks() throws IOException {
        Session session = sessionFactory.getCurrentSession();
        List<Book> books = session.createQuery("select b from Book b", Book.class).getResultList();

        return books;
    }

    @Transactional
    public void saveBooks(Book book) throws IOException {
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);
    }
    @Transactional
    public void deleteBook(Long id) throws IOException {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<Book> delete = builder.createCriteriaDelete(Book.class);

        Root<Book> root = delete.from(Book.class);
        delete.where(builder.equal(root.get("id"), id));
        session.createQuery(delete).executeUpdate();

    }
    @Transactional
    public void update(Long id, Book updateBook){
        Session session = sessionFactory.getCurrentSession();
        Book bookToBeUpdated = session.get(Book.class, id);

        bookToBeUpdated.setName(bookToBeUpdated.getName());
        bookToBeUpdated.setAuthor(bookToBeUpdated.getAuthor());
        bookToBeUpdated.setDescription(bookToBeUpdated.getDescription());

        session.merge(updateBook);
    }
}

