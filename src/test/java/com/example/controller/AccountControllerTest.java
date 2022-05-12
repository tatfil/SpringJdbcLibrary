package com.example.controller;


import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.example.exception.DAOException;
import com.example.model.Account;
import com.example.model.AccountDTO;
import com.example.model.BookItem;
import com.example.model.Patron;
import com.example.service.AccountService;
import com.example.service.BookItemService;
import com.example.service.PatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;


@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    private static final int TEST_ACCOUNT_ID = 1;
    private static final int TEST_BOOK_ITEM_ID = 1;
    private static final int TEST_PATRON_ID = 1;

    @MockBean
    PatronService patronService;

    @MockBean
    BookItemService bookItemService;

    @MockBean
    AccountService accountService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws DAOException {
        var patron = new Patron(TEST_PATRON_ID, "name", "M, Main str. 1");
        given(this.patronService.findById(TEST_PATRON_ID)).willReturn(Optional.of(new Patron()));
        given(this.patronService.save(patron)).willReturn(patron);

        given(this.accountService.findById(TEST_ACCOUNT_ID)).willReturn(Optional.of(new Account()));
        given(this.bookItemService.findById(TEST_BOOK_ITEM_ID)).willReturn(Optional.of(new BookItem()));
    }

    @Test
    public void testShowAccount() throws Exception {
        Account account = new Account(null, null, "ACTIVE");
        AccountDTO accountDTO = new AccountDTO(account);
        accountDTO.setPatronName("Patron name");

        given(this.accountService.findById(TEST_ACCOUNT_ID)).willReturn(Optional.of(account));
        given(this.accountService.getAccountDTO(account)).willReturn(accountDTO);

        mockMvc.perform(get("/accounts/{accountId}", TEST_ACCOUNT_ID)).andExpect(status().isOk())
                .andExpect(model().attribute("accountDTO", hasProperty("patronName", is("Patron name"))))
                .andExpect(model().attribute("accountDTO", hasProperty("state", is("ACTIVE"))))
                .andExpect(view().name("accounts/accountDetails"));
    }


    @Test
    public void testNewAccountForm() throws Exception {
        mockMvc.perform(get("/accounts/newAccount")).andExpect(status().isOk())
                .andExpect(view().name("accounts/newAccount.html")).andExpect(model().attributeExists("account"));
    }

    @Test
    public void testProcessNewAccountFormSuccess() throws Exception {
        var patron = new Patron(null, "name", "M, Main str. 1");
        var account = new Account(null, TEST_PATRON_ID, "ACTIVE");

        given(this.patronService.save(patron)).willReturn(new Patron(TEST_PATRON_ID, patron.getName(), patron.getAddress()));
        given(this.accountService.save(account)).willReturn(account);

        mockMvc
                .perform(post("/accounts/newAccount", account, patron).param("name", "name").param("address", "M, Main str. 1")
                        .param("state", "ACTIVE"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testInitBorrowBookForm() throws Exception {
        mockMvc.perform(get("/accounts/{accountId}/books/borrowNew", TEST_ACCOUNT_ID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bookItem"))
                .andExpect(model().attributeExists("accountId"));
    }

    @Test
    public void testProcessBorrowBookFormSuccess() throws Exception {
        BookItem bookItem = new BookItem();
        bookItem.setId(TEST_BOOK_ITEM_ID);
        bookItem.setTitle("Jungle Book");
        given(this.bookItemService.findByTitle(bookItem.getTitle())).willReturn(Optional.of(bookItem));
        given(this.bookItemService.validateBookItem(bookItem)).willReturn(new String());
        mockMvc
                .perform(post("/accounts/{accountId}/books/borrowNew", TEST_ACCOUNT_ID).param("title", "Jungle Book"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testProcessBorrowBookFormHasErrors() throws Exception {
        BookItem bookItem = new BookItem();
        bookItem.setId(TEST_BOOK_ITEM_ID);
        bookItem.setTitle("Jungle Book");
        given(this.bookItemService.findByTitle(bookItem.getTitle())).willReturn(Optional.of(bookItem));
        given(this.bookItemService.validateBookItem(bookItem)).willReturn(new String(bookItem.getTitle()+ " is not available" ));
        mockMvc.perform(post("/accounts/{accountId}/books/borrowNew", TEST_ACCOUNT_ID).param("title", "Jungle Book"))
                .andExpect( model().hasErrors())
                .andExpect(view().name("accounts/borrowBook"));
    }



}
