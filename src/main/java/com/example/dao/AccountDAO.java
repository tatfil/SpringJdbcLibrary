package com.example.dao;

import com.example.exception.DAOException;
import com.example.model.Account;

public interface AccountDAO extends DAO<Account, Integer> {

    String getPatronName(Account account) throws DAOException;

    void changeState(String state, Account account);
}
