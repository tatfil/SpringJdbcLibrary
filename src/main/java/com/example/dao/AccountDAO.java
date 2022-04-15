package com.example.dao;

import com.example.model.Account;

public interface AccountDAO extends DAO<Account, Integer> {

    void changeState(String state, Account account);
}
