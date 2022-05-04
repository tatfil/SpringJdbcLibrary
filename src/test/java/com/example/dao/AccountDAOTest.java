package com.example.dao;

import com.example.exception.DAOException;
import com.example.exception.EntityException;
import com.example.model.Account;

import com.example.model.BookItem;
import com.example.model.Patron;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class AccountDAOTest {

    @Autowired
    AccountDAO accountDAO;

    @Autowired
    PatronDAO patronDAO;

    @Autowired
    BookItemDAO bookDAO;

    @Test
    void testSave() throws DAOException {
        Patron patron = new Patron("Aaaaa Bbbbb", "");
        patron.setId(patronDAO.save(patron).getId());

        Account account = new Account(patron.getId(), "");
        account.setId(accountDAO.save(account).getId());

        Optional<Account> accountDB = accountDAO.findById(account.getId());
        assertEquals(Optional.of(account), accountDB);
    }

    @Test
    void testDelete() throws DAOException, EntityException {
        Patron patron = new Patron("Aaaaa Bbbbb", "");
        patron.setId(patronDAO.save(patron).getId());

        Account account = new Account(patron.getId(), "");
        account.setId(accountDAO.save(account).getId());

        accountDAO.deleteById(account.getId());

        assertEquals(Optional.empty(), accountDAO.findById(account.getId()));
    }

    @Test
    void testUpdate() throws DAOException {
        Patron patron1 = new Patron("Aaaaa Bbbbb", "");
        patron1.setId(patronDAO.save(patron1).getId());

        Account account = new Account(patron1.getId(), "");
        account.setId(accountDAO.save(account).getId());

        Patron patron2 = new Patron("Ccccc Ddddd", "");
        patron2.setId(patronDAO.save(patron2).getId());

        Account accountUpdated = new Account(account.getId(), patron2.getId(), "");
        accountDAO.save(accountUpdated);
        List<Account> accountDB = accountDAO.findAll();

        assertTrue(accountDB.contains(accountUpdated));
    }

    @Test
    void addAndRemoveBookTest()  throws DAOException{
        BookItem book = new BookItem(1, "The Adventures of Tom Sawyer", 111, null, null);
        book.setId(bookDAO.save(book).getId());

        Patron patron = new Patron("Aaaaa Bbbbb", "");
        patron.setId(patronDAO.save(patron).getId());

        Account account = new Account(patron.getId(), "");
        account.setId(accountDAO.save(account).getId());

        accountDAO.addBookToAccount(book, account);
        List <BookItem> test = accountDAO.getBooksFromAccount(account);
        assertTrue(test.contains(book));

        accountDAO.removeBookFromAccount(book.getId(), account.getId());

        assertFalse(accountDAO.getBooksFromAccount(account).contains(book));
    }
}
