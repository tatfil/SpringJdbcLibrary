package com.example.dao;

import com.example.exception.DAOException;
import com.example.exception.EntityException;
import com.example.model.Patron;

import java.util.List;
import java.util.Optional;

public interface PatronDAO extends DAO<Patron, Integer> {

     Optional <Patron> findByName(String name) throws DAOException;
}
