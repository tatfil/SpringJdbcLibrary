package com.example.controller;

import com.example.exception.DAOException;
import com.example.model.BookItem;
import com.example.model.Patron;
import com.example.service.BookItemService;
import com.example.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PatronController {

    @Autowired
    private final PatronService patronService;

    @Autowired
    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }
    @GetMapping("/patrons")
    public String getPatrons(Model model) throws DAOException {

        List<Patron> bookItems = patronService.findAll();
        model.addAttribute("patronPage", bookItems);
        return "patrons.html";
    }

    @GetMapping("/newPatron")
    public String newPatron(Model model) {
        model.addAttribute("patron", new Patron());
        return "newPatron";
    }

    @PostMapping("/newPatron")
    public String newPatron(@ModelAttribute("patron") Patron patron, Model model) throws DAOException {
        patronService.save(patron);

        getPatrons(model);
        return "patrons.html";
    }
}
