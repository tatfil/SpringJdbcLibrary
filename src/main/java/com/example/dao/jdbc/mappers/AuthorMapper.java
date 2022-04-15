package com.example.dao.jdbc.mappers;

import com.example.model.Author;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AuthorMapper implements RowMapper<Author> {
    private static final String ID = "id";
    private static final String NAME = "name";

    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getInt(ID));
        author.setName(resultSet.getString(NAME));
        return author;
    }
}