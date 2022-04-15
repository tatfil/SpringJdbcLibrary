package com.example.controller;

import com.example.exception.DAOException;
import com.example.model.Account;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public String getLessons(Model model) throws DAOException {

        List<Account> accounts = accountService.findAll();
        model.addAttribute("accountPage", accounts);
        return "accounts.html";
    }
}
