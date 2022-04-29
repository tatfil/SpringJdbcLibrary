package com.example.service;

import com.example.exception.DAOException;
import com.example.exception.EntityException;
import com.example.model.Account;
import com.example.model.AccountDTO;
import com.example.model.BookItem;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    void changeState(String state, Account account);

    Optional<Account> findById(Integer id) throws DAOException;

    List<Account> findAll() throws DAOException;

    Account save(Account entity) throws DAOException;

    void deleteById(Integer id) throws DAOException, EntityException;

    AccountDTO getAccountDTO(Account account) throws DAOException;

    void addBookToAccount(BookItem book, Account account) throws DAOException;

    List<BookItem> getBooksFromAccount(Account account) throws DAOException;

    void removeBookFromAccount(Integer bookItemId, Integer accountId)  throws DAOException;
}
