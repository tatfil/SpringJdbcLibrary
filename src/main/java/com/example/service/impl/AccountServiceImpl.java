package com.example.service.impl;

import com.example.dao.AccountDAO;
import com.example.exception.DAOException;
import com.example.exception.EntityException;
import com.example.model.Account;
import com.example.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDAO accountDAO;

    public AccountServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void changeState(String state, Account account) {
        accountDAO.changeState(state, account);
    }

    @Override
    public Optional<Account> findById(Integer id) throws DAOException {
        return accountDAO.findById(id);
    }

    @Override
    public List<Account> findAll() throws DAOException {
        return accountDAO.findAll();
    }

    @Override
    public Account save(Account entity) throws DAOException {
        return accountDAO.save(entity);
    }

    @Override
    public void deleteById(Integer id) throws DAOException, EntityException {
        accountDAO.deleteById(id);
    }


}
