package com.example.dao;

import com.example.exception.DAOException;
import com.example.model.Account;
import com.example.model.BookItem;

import java.util.List;
import java.util.Optional;

public interface BookItemDAO extends DAO<BookItem, Integer>{
    Optional<BookItem> findByTitle(String title)  throws DAOException;

    void addBookToAccount(BookItem book, Account account) throws DAOException;

    List<BookItem> getBooksFromAccount(Account account) throws DAOException;

    void removeBookFromAccount(BookItem book, Account account)  throws DAOException;
}
