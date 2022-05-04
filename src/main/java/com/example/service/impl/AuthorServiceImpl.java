package com.example.service.impl;

import com.example.dao.AuthorDAO;
import com.example.exception.DAOException;
import com.example.exception.EntityException;
import com.example.model.Author;
import com.example.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    AuthorDAO authorDAO;

    public AuthorServiceImpl(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    @Override
    public Optional<Author> findById(Integer id) throws DAOException {
        return authorDAO.findById(id);
    }

    @Override
    public List<Author> findAll() throws DAOException {
        return authorDAO.findAll();
    }

    @Override
    public Author save(Author entity) throws DAOException {
        return authorDAO.save(entity);
    }

    @Override
    public void deleteById(Integer id) throws DAOException, EntityException {
        authorDAO.deleteById(id);
    }

    @Override
    public Optional<Author> findByName(String name) throws DAOException {
        return authorDAO.findByName(name);
    }
}
