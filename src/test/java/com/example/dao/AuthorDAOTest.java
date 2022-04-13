package com.example.dao;
import com.example.exception.DAOException;
import com.example.exception.EntityException;
import com.example.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthorDAOTest{

    @Autowired
    AuthorDAO authorDAO;

    @Test
    void testSave() throws DAOException {
        Author author = new Author("Mark Twain");
        author.setId(authorDAO.save(author).getId());

        Optional<Author> authorDB = authorDAO.findById(author.getId());
        assertEquals(Optional.of(author), authorDB);

        assertEquals(Optional.of(author), authorDAO.findByName(author.getName()));
    }

    @Test
    void testDelete() throws DAOException, EntityException {
        Author author = new Author("Mark Twain");
        author.setId(authorDAO.save(author).getId());

        authorDAO.deleteById(author.getId());

        assertEquals(Optional.empty(), authorDAO.findById(author.getId()));
    }

    @Test
    void testUpdate() throws DAOException {
        Author author = new Author("Mark Twain");
        author.setId(authorDAO.save(author).getId());

        Author authorUpdated = new Author(author.getId(), "Another name");
        authorDAO.save(authorUpdated);
        List<Author> groupsFromDB = authorDAO.findAll();

        assertTrue(groupsFromDB.contains(authorUpdated));
    }

}