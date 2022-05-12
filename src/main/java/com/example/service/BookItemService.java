package com.example.service;

import com.example.exception.DAOException;
import com.example.exception.EntityException;
import com.example.model.BookItem;
import com.example.model.BookItemDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookItemService {

    Optional<BookItem> findById(Integer id) throws DAOException;

    List<BookItem> findAll() throws DAOException;

    BookItem save(BookItem entity) throws DAOException;

    void deleteById(Integer id) throws DAOException, EntityException;

    Optional<BookItem> findByTitle(String name)  throws DAOException;



    void addBookToAuthor(Integer bookId, Integer authorId) throws DAOException;

    Date setDueToDate(Date startDate);

    BookItemDTO getBookItemDto(BookItem bookItem) throws DAOException;

    String validateBookItem(BookItem bookItem);
}
