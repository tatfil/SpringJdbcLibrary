package com.example.dao;

import com.example.exception.DAOException;
import com.example.model.Account;
import com.example.model.BookItem;

import java.util.List;

public interface AccountDAO extends DAO<Account, Integer> {

    String getPatronName(Account account) throws DAOException;

    void changeState(String state, Account account);

    void addBookToAccount(BookItem book, Account account) throws DAOException;

    List<BookItem> getBooksFromAccount(Account account) throws DAOException;

    void removeBookFromAccount(BookItem book, Integer accountId)  throws DAOException;
}
