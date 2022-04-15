package com.example.dao;

import com.example.exception.DAOException;
import com.example.exception.EntityException;

import com.example.model.Patron;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PatronDAOTest {

    @Autowired
    PatronDAO patronDAO;

    @Test
    void testSave() throws DAOException {
        Patron patron = new Patron("Aaaaa Bbbbb", "");
        patron.setId(patronDAO.save(patron).getId());

        Optional<Patron> patronDB = patronDAO.findById(patron.getId());
        assertEquals(Optional.of(patron), patronDB);

        assertEquals(Optional.of(patron), patronDAO.findByName(patron.getName()));
    }

    @Test
    void testDelete() throws DAOException, EntityException {
        Patron patron = new Patron("Aaaaa Bbbbb", "");
        patron.setId(patronDAO.save(patron).getId());

        patronDAO.deleteById(patron.getId());

        assertEquals(Optional.empty(), patronDAO.findById(patron.getId()));
    }

    @Test
    void testUpdate() throws DAOException {
        Patron patron = new Patron("Aaaaa Bbbbb", "");
        patron.setId(patronDAO.save(patron).getId());

        Patron patronUpdated = new Patron(patron.getId(), "Another name", "");
        patronDAO.save(patronUpdated);
        List<Patron> patronDB = patronDAO.findAll();

        assertTrue(patronDB.contains(patronUpdated));
    }
}