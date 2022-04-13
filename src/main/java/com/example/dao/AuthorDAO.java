package com.example.dao;

import com.example.exception.DAOException;
import com.example.model.Author;

import java.util.Optional;

public interface AuthorDAO extends DAO<Author, Integer>{
    Optional<Author> findByName(String name)  throws DAOException;

}
