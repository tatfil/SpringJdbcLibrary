package com.example.controller;

import com.example.exception.DAOException;
import com.example.model.Account;
import com.example.model.AccountDTO;
import com.example.model.Patron;
import com.example.service.AccountService;
import com.example.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class AccountController {
    private final AccountService accountService;
    private final PatronService patronService;


    @Autowired
    public AccountController(AccountService accountService, PatronService patronService) {
        this.accountService = accountService;
        this.patronService = patronService;
    }

    @GetMapping("/accounts")
    public String getAccounts(Model model) throws DAOException {

        List<Account> accounts = accountService.findAll();
        List<AccountDTO> accountDTOS = new ArrayList<>();

        for (Account account: accounts) {
            accountDTOS.add(accountService.getAccountDTO(account));
        }

        model.addAttribute("accountPage", accountDTOS);
        return "accounts.html";
    }

    @GetMapping("/newAccount")
    public String newAccount(Model model) {
        model.addAttribute("account", new Account());
        model.addAttribute("patron", new Patron());
        return "newAccount.html";
    }


    @PostMapping("/newAccount")
    public String newAccount(@ModelAttribute("account") Account account, @ModelAttribute("patron")Patron patron, Model model) throws DAOException {

        Integer patronId = patronService.save(patron).getId();

        Account account1 = new Account();
        account1.setPatronId(patronId);
        account1.setState(account.getState());
        accountService.save(account1);

        getAccounts(model);
        return "accounts.html";
    }
}
