package com.example.dao;


import com.example.exception.DAOException;
import com.example.exception.EntityException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DAO<T, K> {

    Optional<T> findById(Integer id) throws DAOException;

    List<T> findAll() throws DAOException;

    T save(T entity) throws DAOException;

    void deleteById(Integer id) throws DAOException, EntityException; // if not deleted throw exception

}
