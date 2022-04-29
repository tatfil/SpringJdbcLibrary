package com.example.dao.jdbc.mappers;

import com.example.model.Patron;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PatronMapper implements RowMapper<Patron> {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";

    @Override
    public Patron mapRow(ResultSet resultSet, int i) throws SQLException {
        Patron patron = new Patron();
        patron.setId(resultSet.getInt(ID));
        patron.setName(resultSet.getString(NAME));
        patron.setAddress(resultSet.getString(ADDRESS));
        return patron;
    }
}