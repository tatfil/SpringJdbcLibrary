package com.example.service.impl;


import com.example.service.AccountService;
import com.example.service.BookItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryServiceImpl {

    AccountService accountService;
    BookItemService bookItemService;

    @Autowired
    public LibraryServiceImpl(AccountService accountService, BookItemService bookItemService) {
        this.accountService = accountService;
        this.bookItemService = bookItemService;
    }
}
