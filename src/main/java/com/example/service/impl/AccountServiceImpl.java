package com.example.service.impl;

import com.example.dao.AccountDAO;
import com.example.exception.DAOException;
import com.example.exception.EntityException;
import com.example.model.Account;
import com.example.model.AccountDTO;
import com.example.model.BookItem;
import com.example.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Override
    public AccountDTO getAccountDTO(Account account) throws DAOException {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setPatronName(accountDAO.getPatronName(account));
        accountDTO.setState(account.getState());
        accountDTO.setBorrowedItems(Set.copyOf(accountDAO.getBooksFromAccount(account)));
        return accountDTO;
    }

    @Override
    public void addBookToAccount(BookItem book, Account account) throws DAOException {
        accountDAO.addBookToAccount(book, account);
    }

    @Override
    public List<BookItem> getBooksFromAccount(Account account) throws DAOException {
        return accountDAO.getBooksFromAccount(account);
    }

    @Override
    public void removeBookFromAccount(Integer bookItemId, Integer accountId) throws DAOException {
        accountDAO.removeBookFromAccount(bookItemId, accountId);
    }
}
