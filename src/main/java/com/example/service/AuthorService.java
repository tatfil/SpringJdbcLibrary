package com.example.service;

import com.example.exception.DAOException;
import com.example.exception.EntityException;
import com.example.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(Integer id) throws DAOException;

    List<Author> findAll() throws DAOException;

    Author save(Author entity) throws DAOException;

    void deleteById(Integer id) throws DAOException, EntityException;

    Optional<Author> findByName(String name)  throws DAOException;
}
