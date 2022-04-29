package com.example.service.impl;

import com.example.dao.PatronDAO;
import com.example.exception.DAOException;
import com.example.exception.EntityException;
import com.example.model.Patron;
import com.example.service.PatronService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronServiceImpl implements PatronService {
    PatronDAO patronDAO;

    public PatronServiceImpl(PatronDAO patronDAO) {
        this.patronDAO = patronDAO;
    }

    @Override
    public Optional<Patron> findById(Integer id) throws DAOException {
        return patronDAO.findById(id);
    }

    @Override
    public List<Patron> findAll() throws DAOException {
        return patronDAO.findAll();
    }

    @Override
    public Patron save(Patron entity) throws DAOException {
        return patronDAO.save(entity);
    }

    @Override
    public void deleteById(Integer id) throws DAOException, EntityException {
        patronDAO.deleteById(id);
    }
}
