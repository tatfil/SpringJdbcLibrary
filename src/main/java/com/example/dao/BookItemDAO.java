package com.example.dao;

import com.example.exception.DAOException;
import com.example.model.Author;
import com.example.model.BookItem;

import java.util.Optional;

public interface BookItemDAO extends DAO<BookItem, Integer>{
    Optional<BookItem> findByTitle(String title)  throws DAOException;

    void addBookToAuthor(Integer bookId, Integer authorId) throws DAOException;

    String getAuthorName(Integer bookItemId) throws DAOException;

    void setBorrowedDate(Integer bookItemId) throws DAOException;
}
