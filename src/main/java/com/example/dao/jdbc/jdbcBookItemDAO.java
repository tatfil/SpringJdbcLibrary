package com.example.dao.jdbc;

import com.example.dao.AbstractDAO;
import com.example.dao.BookItemDAO;
import com.example.dao.jdbc.mappers.BookItemMapper;
import com.example.exception.DAOException;
import com.example.exception.EntityException;
import com.example.model.BookItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class jdbcBookItemDAO extends AbstractDAO<BookItem, Integer> implements BookItemDAO {

    private static final String FIND_BY_ID = "SELECT id, title, isbn, barcode, status, borrowed FROM books WHERE id = ?";
    private static final String FIND_BY_TITLE = "SELECT id, title, isbn, barcode, status, borrowed FROM books WHERE title = ?";
    private static final String GET_ALL = "SELECT * FROM books";
    private static final String UPDATE = "UPDATE books SET title =?, isbn =?, barcode =?, status =?, borrowed =? WHERE id = ?";
    private static final String DELETE = "DELETE FROM books where id = ?";


    private static final String ADD_BOOK_TO_AUTHOR = "INSERT INTO authors_books(author_id, book_id) VALUES ( ?, ?) ";;
    private static final String GET_AUTHOR_NAME = "SELECT authors.name FROM authors " +
            "INNER JOIN authors_books on authors.id = authors_books.author_id " +
            "WHERE authors_books.book_id = ? ";

    private static final String SET_BOOK_BORROWED_DATE = "";

    Logger logger = LoggerFactory.getLogger("JdbcBookItemDao");


    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public jdbcBookItemDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new JdbcTemplate(Objects.requireNonNull(jdbcTemplate.getDataSource()));
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("books").usingGeneratedKeyColumns("id");
    }
    @Override
    protected BookItem create(BookItem entity) throws DAOException {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("ID", entity.getId());
        parameters.put("title", entity.getTitle());
        parameters.put("isbn", entity.getIsbn());
        parameters.put("barcode", entity.getBarcode());
        parameters.put("status", entity.getStatus());
        parameters.put("borrowed", entity.getBorrowed());


        try {
            entity.setId((int) simpleJdbcInsert.executeAndReturnKey(parameters));
            return entity;
        } catch (DataAccessException e){
            logger.warn("Failed to create book '{}'", entity);
            throw new DAOException(e, entity);
        }
    }

    @Override
    protected BookItem update(BookItem entity) throws DAOException {
        try {
            if (this.jdbcTemplate.update(UPDATE, entity.getTitle(), entity.getIsbn(), entity.getBarcode(),
                    entity.getStatus(), entity.getBorrowed(),  entity.getId()) ==0){
                logger.warn("Did not update the book '{}'", entity);
                throw new DAOException("Did not update the book " + entity);
            }
            return entity;
        } catch (DataAccessException e){
            logger.warn("Failed to update book '{}'", entity);
            throw new DAOException(e, entity);
        }
    }

    @Override
    public Optional<BookItem> findById(Integer id) throws DAOException {
        try{
            return Optional.ofNullable(jdbcTemplate.query(
                    FIND_BY_ID,
                    rs -> rs.next() ? new BookItemMapper().mapRow(rs, 1) : null,
                    id));
        } catch (DataAccessException e){
            logger.warn("Failed to find book '{}' by id", id);
            throw new DAOException(e, id);
        }
    }

    @Override
    public List<BookItem> findAll() throws DAOException {
        try {
            return this.jdbcTemplate.query(GET_ALL, new BookItemMapper());
        } catch (DataAccessException e){
            logger.warn("Failed to find all books");
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteById(Integer id) throws DAOException, EntityException {
        try {
            if (jdbcTemplate.update(DELETE, id) == 0) {
                logger.warn("Failed to delete book by id '{}'", id);
                throw new DAOException("Failed to delete book by id " + id);
            }
        } catch (DataAccessException e){
            logger.warn("Failed to delete book by id '{}'", id);
            throw new DAOException("Failed to delete book by id " + id);
        }
    }

    @Override
    public Optional<BookItem> findByTitle(String title) throws DAOException {
        try {
            return Optional.ofNullable(jdbcTemplate.query(
                    FIND_BY_TITLE,
                rs -> rs.next() ? new BookItemMapper().mapRow(rs, 1) : null,
                title));
    } catch (DataAccessException e){
        logger.warn("Failed to find book '{}' by name", title);
        throw new DAOException(e, title);
    }
    }



    @Override
    public void addBookToAuthor(Integer bookId, Integer authorId) throws DAOException {
        try {
            jdbcTemplate.update(ADD_BOOK_TO_AUTHOR, authorId, bookId);
        } catch (DataAccessException e) {
            logger.warn("Failed to add book '{}' to author '{}'", bookId, authorId);
            throw new DAOException(e, bookId, authorId);
        }
    }

    @Override
    //todo Optional
    public String getAuthorName(Integer bookItemId) throws DAOException {
        try {
            return this.jdbcTemplate.queryForObject(GET_AUTHOR_NAME, String.class, bookItemId);
        } catch (DataAccessException e) {
            logger.warn("Failed to get author of book '{}'", bookItemId);
            throw new DAOException(e, bookItemId);
        }

    }

    @Override
    public void setBorrowedDate(Integer bookItemId) throws DAOException {
        try {
            jdbcTemplate.update(SET_BOOK_BORROWED_DATE, bookItemId);
        } catch (DataAccessException e) {
            logger.warn("Failed to set book borrowed date '{}'", bookItemId);
            throw new DAOException(e, bookItemId);
        }
    }
}
