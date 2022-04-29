package com.example.service;

import com.example.exception.DAOException;
import com.example.exception.EntityException;
import com.example.model.Account;
import com.example.model.Patron;

import java.util.List;
import java.util.Optional;

public interface PatronService {
    Optional<Patron> findById(Integer id) throws DAOException;

    List<Patron> findAll() throws DAOException;

    Patron save(Patron entity) throws DAOException;

    void deleteById(Integer id) throws DAOException, EntityException;
}
