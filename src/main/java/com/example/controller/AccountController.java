package com.example.controller;
import javax.validation.Valid;

import com.example.exception.BookAvailableException;
import com.example.exception.DAOException;
import com.example.model.Account;
import com.example.model.AccountDTO;
import com.example.model.BookItem;
import com.example.model.Patron;
import com.example.service.AccountService;
import com.example.service.BookItemService;
import com.example.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@Controller
public class AccountController {
    private final AccountService accountService;
    private final PatronService patronService;
    private final BookItemService bookItemService;


    @Autowired
    public AccountController(AccountService accountService, PatronService patronService, BookItemService bookItemService) {
        this.accountService = accountService;
        this.patronService = patronService;
        this.bookItemService = bookItemService;
    }

    @GetMapping("/accounts/accountsList")
    public String getAccounts(Model model) throws DAOException {

        List<Account> accounts = accountService.findAll();
        List<AccountDTO> accountDTOS = new ArrayList<>();

        for (Account account: accounts) {
            accountDTOS.add(accountService.getAccountDTO(account));
        }

        model.addAttribute("accountPage", accountDTOS);
        return "accounts/accountsList.html";
    }

    @GetMapping("/accounts/newAccount")
    public String newAccount(Model model) {
        model.addAttribute("account", new Account());
        model.addAttribute("patron", new Patron());
        return "accounts/newAccount.html";
    }


    @PostMapping("/accounts/newAccount")
    public String newAccount(Account account, Patron patron) throws DAOException {
        account.setPatronId(patronService.save(patron).getId());
        account.setId(accountService.save(account).getId());
        return "redirect:/accounts/" + account.getId();
    }

    @GetMapping("/accounts/{accountId}")
    public String showAccount(@PathVariable("accountId") int accountId, Model model) throws DAOException {

        Account account = accountService.findById(accountId).get();
        AccountDTO accountDTO = accountService.getAccountDTO(account);
        model.addAttribute("accountDTO", accountDTO);
        model.addAttribute("bookItem", new BookItem());
        model.addAttribute("java8Instant", LocalDate.now());
        return "accounts/accountDetails";
    }

    @GetMapping("/accounts/{accountId}/books/{bookId}/return")
    public String returnBookItem(@PathVariable("accountId") int accountId, @PathVariable("bookId") int bookItemId, Model model) throws DAOException {
        Account account = accountService.findById(accountId).get();
        BookItem bookItem = bookItemService.findById(bookItemId).get();
        accountService.removeBookFromAccount(bookItem, accountId);

        AccountDTO accountDTO = accountService.getAccountDTO(account);
        model.addAttribute("accountDTO", accountDTO);
        model.addAttribute("bookItem", new BookItem());
        return "redirect:/accounts/" + account.getId();
    }


    @GetMapping("/accounts/{accountId}/books/borrowNew")
    public String borrowBookItem(@PathVariable("accountId") int accountId, Model model) throws DAOException {
        model.addAttribute("accountId", accountId);
        model.addAttribute("bookItem", new BookItem());
        return "accounts/borrowBook";
    }


    @PostMapping("/accounts/{accountId}/books/borrowNew")
    public String borrowBookItem(@PathVariable("accountId") int accountId, @Valid BookItem bookItem, BindingResult result, Model model) throws DAOException, BookAvailableException {

        BookItem bookItemNew = bookItemService.findByTitle(bookItem.getTitle()).get();

        String err = bookItemService.validateBookItem(bookItemNew);
        if (!err.isEmpty()) {
            ObjectError error = new ObjectError("globalError", err);
            result.addError(error);
        }
        if (result.hasErrors()) {
            return "accounts/borrowBook";
        }

        LocalDate localDate = LocalDate.now();

//        bookItemNew.setBorrowed(java.util.Date.from(localDate.atStartOfDay()
//                .atZone(ZoneId.systemDefault())
//                .toInstant()));

        bookItemNew.setBorrowed(localDate);

        bookItemService.save(bookItemNew);

        Account account = accountService.findById(accountId).get();
        accountService.addBookToAccount(bookItemNew, account);

        AccountDTO accountDTO = accountService.getAccountDTO(account);
        model.addAttribute("accountDTO", accountDTO);
        model.addAttribute("bookItem", bookItemNew);
        return "redirect:/accounts/" + account.getId();
    }
}
