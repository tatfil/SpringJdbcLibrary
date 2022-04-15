package com.example.service;

import com.example.exception.DAOException;
import com.example.exception.EntityException;
import com.example.model.Account;
import com.example.model.BookItem;

import java.util.List;
import java.util.Optional;

public interface BookItemService {

    Optional<BookItem> findById(Integer id) throws DAOException;

    List<BookItem> findAll() throws DAOException;

    BookItem save(BookItem entity) throws DAOException;

    void deleteById(Integer id) throws DAOException, EntityException;

    Optional<BookItem> findByTitle(String name)  throws DAOException;

    void addBookToAccount(BookItem book, Account account) throws DAOException;

    List<BookItem> getBooksFromAccount(Account account) throws DAOException;

    void removeBookFromAccount(BookItem book, Account account)  throws DAOException;

}
