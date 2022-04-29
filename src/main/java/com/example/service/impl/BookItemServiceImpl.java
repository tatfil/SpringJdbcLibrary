package com.example.service.impl;

import com.example.dao.BookItemDAO;
import com.example.exception.DAOException;
import com.example.exception.EntityException;
import com.example.model.Account;
import com.example.model.Author;
import com.example.model.BookItem;
import com.example.service.BookItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookItemServiceImpl implements BookItemService {

    private final BookItemDAO bookItemDAO;


    public BookItemServiceImpl(BookItemDAO bookItemDAO) {
        this.bookItemDAO = bookItemDAO;
    }

    @Override
    public Optional<BookItem> findById(Integer id) throws DAOException {
        return bookItemDAO.findById(id);
    }

    @Override
    public List<BookItem> findAll() throws DAOException {
        return bookItemDAO.findAll();
    }

    @Override
    public BookItem save(BookItem entity) throws DAOException {
        return bookItemDAO.save(entity);
    }

    @Override
    public void deleteById(Integer id) throws DAOException, EntityException {
        bookItemDAO.deleteById(id);
    }

    @Override
    public Optional<BookItem> findByTitle(String title) throws DAOException {
        return bookItemDAO.findByTitle(title);
    }



    @Override
    public void addBookToAuthor(BookItem book, Author author) throws DAOException {
        bookItemDAO.addBookToAuthor(book, author);
    }
}
