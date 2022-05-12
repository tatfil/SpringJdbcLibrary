package com.example.service.impl;

import com.example.dao.BookItemDAO;
import com.example.exception.DAOException;
import com.example.exception.EntityException;
import com.example.model.BookItem;
import com.example.model.BookItemDTO;
import com.example.model.BookStatus;
import com.example.service.BookItemService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookItemServiceImpl implements BookItemService {

    private final BookItemDAO bookItemDAO;


    public BookItemServiceImpl(BookItemDAO bookItemDAO) {
        this.bookItemDAO = bookItemDAO;
    }

    @Override
    public Optional<BookItem> findById(Integer id) throws DAOException {
        return bookItemDAO.findById(id);
    }

    @Override
    public List<BookItem> findAll() throws DAOException {
        return bookItemDAO.findAll();
    }

    @Override
    public BookItem save(BookItem entity) throws DAOException {
        return bookItemDAO.save(entity);
    }

    @Override
    public void deleteById(Integer id) throws DAOException, EntityException {
        bookItemDAO.deleteById(id);
    }

    @Override
    public Optional<BookItem> findByTitle(String title) throws DAOException {
        return bookItemDAO.findByTitle(title);
    }


    @Override
    public void addBookToAuthor(Integer bookId, Integer authorId) throws DAOException {
        bookItemDAO.addBookToAuthor(bookId, authorId);
    }

    @Override
    public Date setDueToDate(Date startDate){
        LocalDateTime localDateTime = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.plusDays(BookItem.CIRCULATION_PERIOD);
        return  Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public BookItemDTO getBookItemDto(BookItem bookItem) throws DAOException {
        BookItemDTO dto = new BookItemDTO(bookItem.getId(), bookItem.getIsbn(), bookItem.getTitle(),
                bookItem.getBarcode(), bookItem.getStatus());
        dto.setAuthorName(bookItemDAO.getAuthorName(bookItem.getId()));

        //todo
        //dto.setDueToDate((java.sql.Date) setDueToDate(bookItem.getBorrowed()));
        return dto;
    }

    //todo Title isn't unique! Use barcode
    @Override
    public String validateBookItem(BookItem bookItem) {
        String message = "";
        if (!bookItem.getStatus().equals(BookStatus.AVAILABLE.toString())) {
            message = bookItem.getTitle() + " is not available";
        }
        return message;
    }

}
