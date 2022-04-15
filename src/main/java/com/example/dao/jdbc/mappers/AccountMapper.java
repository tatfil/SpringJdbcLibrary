package com.example.dao.jdbc.mappers;

import com.example.model.Account;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AccountMapper implements RowMapper<Account> {
    private static final String ID = "id";
    private static final String PATRON_ID = "patron_id";
    private static final String STATE = "state";

    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt(ID));
        account.setPatronId(resultSet.getInt(PATRON_ID));
        account.setState(resultSet.getString(STATE));
        return account;
    }
}