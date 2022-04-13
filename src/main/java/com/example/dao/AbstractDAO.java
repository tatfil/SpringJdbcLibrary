package com.example.dao;

import com.example.exception.DAOException;
import com.example.model.Entity;

public abstract class AbstractDAO<T extends Entity<K>, K> implements DAO<T, K> {
    @Override
    public T save(T entity) throws DAOException {
        return entity.getId() == null ? create(entity) : update(entity);
    }

    protected abstract T create(T entity) throws DAOException;

    protected abstract T update(T entity) throws DAOException;
}