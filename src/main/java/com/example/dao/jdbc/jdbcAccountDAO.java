package com.example.dao.jdbc;

import com.example.dao.AbstractDAO;
import com.example.dao.AccountDAO;
import com.example.dao.jdbc.mappers.AccountMapper;
import com.example.dao.jdbc.mappers.BookItemMapper;
import com.example.exception.DAOException;
import com.example.exception.EntityException;
import com.example.model.Account;
import com.example.model.BookItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class jdbcAccountDAO extends AbstractDAO<Account, Integer> implements AccountDAO {

    private static final String FIND_BY_ID = "SELECT id, patron_id, state FROM accounts WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM accounts";
    private static final String UPDATE = "UPDATE accounts SET patron_id = ?, state = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM accounts where id = ?";

    private static final String GET_PATRON_NAME = "SELECT patrons.name FROM patrons "
            + "INNER JOIN accounts "
            + "ON accounts.patron_id = patrons.id "
            + "WHERE accounts.id = ?";

    private static final String ADD_BOOK_TO_ACCOUNT = "INSERT INTO accounts_books(book_id, account_id) VALUES ( ?, ?) ";
    private static final String GET_BOOKS_FROM_ACCOUNT = "SELECT books.id, title, isbn, barcode, status, borrowed "
            + "FROM books "
            + "INNER JOIN accounts_books "
            + "ON books.id = accounts_books.book_id "
            + "WHERE account_id = ?";
    private static final String REMOVE_BOOK_FROM_ACCOUNT = "DELETE FROM accounts_books "
            + "WHERE accounts_books.account_id=? "
            + "AND accounts_books.book_id =?";

    Logger logger = LoggerFactory.getLogger("JdbcAccountDao");


    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public jdbcAccountDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new JdbcTemplate(Objects.requireNonNull(jdbcTemplate.getDataSource()));
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("accounts").usingGeneratedKeyColumns("id");
    }


    @Override
    protected Account create(Account entity) throws DAOException {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("ID", entity.getId());
        parameters.put("patron_id", entity.getPatronId());
        parameters.put("state", entity.getState());

        try {
            entity.setId((int) simpleJdbcInsert.executeAndReturnKey(parameters));
            return entity;
        } catch (DataAccessException e){
            logger.warn("Failed to create account '{}'", entity);
            throw new DAOException(e, entity);
        }

    }

    @Override
    protected Account update(Account entity) throws DAOException {
        try {
            if (this.jdbcTemplate.update(UPDATE, entity.getPatronId(), entity.getState(), entity.getId()) ==0){
                logger.warn("Did not update the account '{}'", entity);
                throw new DAOException("Did not update the account " + entity);
            }
            return entity;
        } catch (DataAccessException e){
            logger.warn("Failed to update account '{}'", entity);
            throw new DAOException(e, entity);
        }
    }

    @Override
    public Optional<Account> findById(Integer id) throws DAOException {
        try{
            return Optional.ofNullable(jdbcTemplate.query(
                    FIND_BY_ID,
                    rs -> rs.next() ? new AccountMapper().mapRow(rs, 1) : null,
                    id));
        } catch (DataAccessException e){
            logger.warn("Failed to find account '{}' by id", id);
            throw new DAOException(e, id);
        }
    }

    @Override
    public List<Account> findAll() throws DAOException {
        try {
            return this.jdbcTemplate.query(GET_ALL, new AccountMapper());
        } catch (DataAccessException e){
            logger.warn("Failed to find all accounts");
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteById(Integer id) throws DAOException, EntityException {
        try {
            if (jdbcTemplate.update(DELETE, id) == 0) {
                logger.warn("Failed to delete account by id '{}'", id);
                throw new DAOException("Failed to delete account by id " + id);
            }
        } catch (DataAccessException e){
            logger.warn("Failed to delete account by id '{}'", id);
            throw new DAOException("Failed to delete account by id " + id);
        }
    }

    @Override
    public String getPatronName(Account account) throws DAOException {
        try {
            return this.jdbcTemplate.queryForObject(GET_PATRON_NAME, String.class, account.getId());
        } catch (DataAccessException e){
            logger.warn("Failed to find patron name");
            throw new DAOException(e);
        }
    }


    @Override
    public void changeState(String state, Account account) {

    }

    @Override
    public void addBookToAccount(BookItem book, Account account) throws DAOException{
        try {
            jdbcTemplate.update(ADD_BOOK_TO_ACCOUNT, book.getId(), account.getId());
        } catch (DataAccessException e) {
            logger.warn("Failed to add book '{}' to account '{}'", book, account.getId());
            throw new DAOException(e, book, account.getId());
        }
    }

    @Override
    public List<BookItem> getBooksFromAccount(Account account) throws DAOException{
        try {
            return jdbcTemplate.query(GET_BOOKS_FROM_ACCOUNT, new BookItemMapper(), account.getId());
        } catch (DataAccessException e){
            logger.warn("Failed to get books from account '{}'", account);
            throw new DAOException(e, account);
        }
    }

    @Override
    public void removeBookFromAccount(Integer bookItemId, Integer accountId)  throws DAOException{
        try {
            jdbcTemplate.update(REMOVE_BOOK_FROM_ACCOUNT, accountId, bookItemId);
        } catch (DataAccessException e){
            logger.warn("Failed to remove book '{}' from account '{}'", bookItemId, accountId);
            throw new DAOException(e, bookItemId, accountId);
        }
    }

}
