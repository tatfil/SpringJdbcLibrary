package com.example.dao.jdbc;

import com.example.dao.AbstractDAO;
import com.example.dao.AuthorDAO;
import com.example.dao.jdbc.mappers.AuthorMapper;
import com.example.exception.DAOException;
import com.example.exception.EntityException;
import com.example.model.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class jdbcAuthorDAO extends AbstractDAO<Author, Integer> implements AuthorDAO {

    private static final String FIND_BY_ID = "SELECT id, name FROM authors WHERE id = ?";
    private static final String FIND_BY_NAME = "SELECT id, name FROM authors WHERE name = ?";
    private static final String GET_ALL = "SELECT * FROM authors";
    private static final String UPDATE = "UPDATE authors SET name = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM authors where id = ?";

    Logger logger = LoggerFactory.getLogger("JdbcAuthorDao");


    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public jdbcAuthorDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new JdbcTemplate(Objects.requireNonNull(jdbcTemplate.getDataSource()));
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("authors").usingGeneratedKeyColumns("id");
    }

    @Override
    protected Author create(Author entity) throws DAOException {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("ID", entity.getId());
        parameters.put("name", entity.getName());

        try {
            entity.setId((int) simpleJdbcInsert.executeAndReturnKey(parameters));
            return entity;
        } catch (DataAccessException e){
            logger.warn("Failed to create author '{}'", entity);
            throw new DAOException(e, entity);
        }
    }

    @Override
    protected Author update(Author entity) throws DAOException {
        try {
            if (this.jdbcTemplate.update(UPDATE, entity.getName(), entity.getId()) ==0){
                logger.warn("Did not update the author '{}'", entity);
                throw new DAOException("Did not update the author " + entity);
            }
            return entity;
        } catch (DataAccessException e){
            logger.warn("Failed to update author '{}'", entity);
            throw new DAOException(e, entity);
        }
    }

    @Override
    public Optional<Author> findById(Integer id) throws DAOException {
        try{
            return Optional.ofNullable(jdbcTemplate.query(
                    FIND_BY_ID,
                    rs -> rs.next() ? new AuthorMapper().mapRow(rs, 1) : null,
                    id));
        } catch (DataAccessException e){
            logger.warn("Failed to find author '{}' by id", id);
            throw new DAOException(e, id);
        }
    }

    @Override
    public List<Author> findAll() throws DAOException {
        try {
            return this.jdbcTemplate.query(GET_ALL, new AuthorMapper());
        } catch (DataAccessException e){
            logger.warn("Failed to find all authors");
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteById(Integer id) throws DAOException, EntityException {
        try {
            if (jdbcTemplate.update(DELETE, id) == 0) {
                logger.warn("Failed to delete author by id '{}'", id);
                throw new DAOException("Failed to delete author by id " + id);
            }
        } catch (DataAccessException e){
            logger.warn("Failed to delete author by id '{}'", id);
            throw new DAOException("Failed to delete author by id " + id);
        }
    }

    @Override
    public Optional<Author> findByName(String name) throws DAOException {
        try {
            return Optional.ofNullable(jdbcTemplate.query(
                    FIND_BY_NAME,
                    rs -> rs.next() ? new AuthorMapper().mapRow(rs, 1) : null,
                    name));
        } catch (DataAccessException e){
            logger.warn("Failed to find author '{}' by name", name);
            throw new DAOException(e, name);
        }
    }


}
