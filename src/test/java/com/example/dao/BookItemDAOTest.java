package com.example.dao;

import com.example.exception.DAOException;
import com.example.exception.EntityException;
import com.example.model.Account;
import com.example.model.Author;
import com.example.model.BookItem;
import com.example.model.Patron;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookItemDAOTest {

    @Autowired
    BookItemDAO bookDAO;

    @Autowired
    AccountDAO accountDAO;

    @Autowired
    PatronDAO patronDAO;

    @Autowired
    AuthorDAO authorDAO;

    @Test
    void testSave() throws DAOException {

        BookItem book = new BookItem(  "The Adventures of Tom Sawyer");
        book.setId(bookDAO.save(book).getId());

        Optional<BookItem> bookDB = bookDAO.findById(book.getId());
        assertEquals(Optional.of(book), bookDB);

        assertEquals(Optional.of(book), bookDAO.findByTitle(book.getTitle()));
    }

    @Test
    void testDelete() throws DAOException, EntityException {
        BookItem book = new BookItem(1, "The Adventures of Tom Sawyer", null, null, null);
        book.setId(bookDAO.save(book).getId());

        bookDAO.deleteById(book.getId());

        assertEquals(Optional.empty(), bookDAO.findById(book.getId()));
    }

    @Test
    void testUpdate() throws DAOException {
        BookItem book = new BookItem(1, "The Adventures of Tom Sawyer", null, null, null);
        book.setId(bookDAO.save(book).getId());

        BookItem bookUpdated = new BookItem(book.getId(), 1, "Another Title",  null, null, null);
        bookDAO.save(bookUpdated);
        List<BookItem> booksDB = bookDAO.findAll();

        assertTrue(booksDB.contains(bookUpdated));
    }

    @Test
    void testUniqueIndexViolation_throwsException() throws DAOException {

        BookItem book = new BookItem(1, "The Adventures of Tom Sawyer", 111, null, null);
        book.setId(bookDAO.save(book).getId());

        BookItem book2 = new BookItem(1, "Tales", 111, null, null);
        assertThrows(DAOException.class, () -> {
            book2.setId(bookDAO.save(book2).getId()) ;});
    }

    @Test
    void testGetAuthorName() throws DAOException {

        String authorName = "Mark Twain";

        Author author = new Author(authorName);
        author.setId(authorDAO.save(author).getId());

        BookItem book = new BookItem(  "The Adventures of Tom Sawyer");
        book.setId(bookDAO.save(book).getId());

        assertEquals(authorName, bookDAO.getAuthorName(book.getId()));

    }

}