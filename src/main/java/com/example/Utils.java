package com.example;

import com.example.exception.DAOException;
import com.example.model.Author;
import com.example.model.Book;
import com.example.model.BookItem;
import com.example.service.AccountService;
import com.example.service.AuthorService;
import com.example.service.BookItemService;
import com.example.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;

public class Utils {

    @Autowired
    private final BookItemService bookItemService;

    @Autowired
    private final AccountService accountService;

    @Autowired
    private final PatronService patronService;

    @Autowired
    private final AuthorService authorService;

    @Autowired
    public Utils(BookItemService bookItemService, AccountService accountService, PatronService patronService, AuthorService authorService) {
        this.bookItemService = bookItemService;
        this.accountService = accountService;
        this.patronService = patronService;
        this.authorService = authorService;
    }

    public void generateTestData() throws DAOException {
        Author author1 = new Author("Mark Twain");
        authorService.save(author1);

        Author author2 = new Author("Rudyard Kipling");
        authorService.save(author2);

        Author author3 = new Author("Michael Bond");
        authorService.save(author3);


        Book book1 = new Book(12234568, "The Adventures of Tom Sawyer");
        BookItem bookItem11 = new BookItem(book1, "11111", "NOT AVAILABLE", null);
        bookItemService.save(bookItem11);

        Book book2 = new Book(45678412, "Jungle Book");
        BookItem bookItem21 = new BookItem(book2, "22222", "AVAILABLE", null);
        bookItemService.save(bookItem21);

        Book book3 = new Book(56485123, "Paddington''s Day Off");
        BookItem bookItem31 = new BookItem(book2, "33333", "NOT AVAILABLE", null);
        bookItemService.save(bookItem31);

        bookItemService.addBookToAuthor(bookItem11, author1);
        bookItemService.addBookToAuthor(bookItem21, author2);
        bookItemService.addBookToAuthor(bookItem31, author3);


    }
}
