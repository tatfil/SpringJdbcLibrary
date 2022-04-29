package com.example.dao;

import com.example.exception.DAOException;
import com.example.model.Account;
import com.example.model.Author;
import com.example.model.BookItem;

import java.util.List;
import java.util.Optional;

public interface BookItemDAO extends DAO<BookItem, Integer>{
    Optional<BookItem> findByTitle(String title)  throws DAOException;

    void addBookToAuthor(BookItem book, Author author) throws DAOException;
}
