package com.example.dao.jdbc.mappers;

import com.example.model.BookItem;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookItemMapper implements RowMapper<BookItem> {
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String ISBN = "isbn";
    private static final String BARCODE = "barcode";
    private static final String STATUS = "status";
    private static final String BORROWED = "borrowed";

    @Override
    public BookItem mapRow(ResultSet resultSet, int i) throws SQLException {
        BookItem book = new BookItem();
        book.setId(resultSet.getInt(ID));
        book.setTitle(resultSet.getString(TITLE));
        book.setIsbn(resultSet.getInt(ISBN));
        book.setBarcode(resultSet.getInt(BARCODE));
        book.setStatus(resultSet.getString(STATUS));
        book.setBorrowed(resultSet.getDate(BORROWED).toLocalDate());
        return book;
    }
}
