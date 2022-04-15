package com.example.dao.jdbc;

import com.example.dao.AbstractDAO;
import com.example.dao.PatronDAO;
import com.example.dao.jdbc.mappers.PatronMapper;
import com.example.exception.DAOException;
import com.example.exception.EntityException;
import com.example.model.Patron;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class jdbcPatronDAO extends AbstractDAO<Patron, Integer> implements PatronDAO {

    private static final String FIND_BY_ID = "SELECT id, name, address FROM patrons WHERE id = ?";
    private static final String FIND_BY_NAME = "SELECT id, name, address FROM patrons WHERE name = ?";
    private static final String GET_ALL = "SELECT * FROM patrons";
    private static final String UPDATE = "UPDATE patrons SET name = ?, address = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM patrons where id = ?";

    Logger logger = LoggerFactory.getLogger("JdbcPatronDao");


    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public jdbcPatronDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new JdbcTemplate(Objects.requireNonNull(jdbcTemplate.getDataSource()));
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("patrons").usingGeneratedKeyColumns("id");
    }



    @Override
    protected Patron create(Patron entity) throws DAOException {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("ID", entity.getId());
        parameters.put("name", entity.getName());
        parameters.put("address", entity.getAddress());

        try {
            entity.setId((int) simpleJdbcInsert.executeAndReturnKey(parameters));
            return entity;
        } catch (DataAccessException e){
            logger.warn("Failed to create patron '{}'", entity);
            throw new DAOException(e, entity);
        }

    }

    @Override
    protected Patron update(Patron entity) throws DAOException {
        try {
            if (this.jdbcTemplate.update(UPDATE, entity.getName(), entity.getAddress(), entity.getId()) ==0){
                logger.warn("Did not update the patron '{}'", entity);
                throw new DAOException("Did not update the patron " + entity);
            }
            return entity;
        } catch (DataAccessException e){
            logger.warn("Failed to update patron '{}'", entity);
            throw new DAOException(e, entity);
        }
    }

    @Override
    public Optional<Patron> findById(Integer id) throws DAOException {
        try{
            return Optional.ofNullable(jdbcTemplate.query(
                    FIND_BY_ID,
                    rs -> rs.next() ? new PatronMapper().mapRow(rs, 1) : null,
                    id));
        } catch (DataAccessException e){
            logger.warn("Failed to find patron '{}' by id", id);
            throw new DAOException(e, id);
        }
    }

    @Override
    public List<Patron> findAll() throws DAOException {
        try {
            return this.jdbcTemplate.query(GET_ALL, new PatronMapper());
        } catch (DataAccessException e){
            logger.warn("Failed to find all patrons");
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteById(Integer id) throws DAOException, EntityException {
        try {
            if (jdbcTemplate.update(DELETE, id) == 0) {
                logger.warn("Failed to delete patron by id '{}'", id);
                throw new DAOException("Failed to delete patron by id " + id);
            }
        } catch (DataAccessException e){
            logger.warn("Failed to delete patron by id '{}'", id);
            throw new DAOException("Failed to delete patron by id " + id);
        }
    }


    @Override
    public  Optional <Patron> findByName(String name) throws DAOException {
        try {
            return Optional.ofNullable(jdbcTemplate.query(
                    FIND_BY_NAME,
                    rs -> rs.next() ? new PatronMapper().mapRow(rs, 1) : null,
                    name));
        } catch (DataAccessException e){
            logger.warn("Failed to find patron '{}' by name", name);
            throw new DAOException(e, name);
        }
    }
}
