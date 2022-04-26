package com.example.controller;

import com.example.exception.DAOException;
import com.example.model.Author;
import com.example.model.Book;
import com.example.model.BookItem;
import com.example.model.SampleInputs;
import com.example.service.BookItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class BookItemController {

    @Autowired
    private final BookItemService bookItemService;

    @Autowired
    public BookItemController(BookItemService bookItemService) {
        this.bookItemService = bookItemService;
    }

    @GetMapping("/books")
    public String getBooks(Model model) throws DAOException {

        List<BookItem> bookItems = bookItemService.findAll();
        model.addAttribute("bookPage", bookItems);
        return "books.html";
    }

    @GetMapping("/newBook")
    public String newBook(Model model) {
        model.addAttribute("bookItem", new BookItem());
        return "newBook";
    }

    @PostMapping("/newBook")
    public String newBook(@ModelAttribute("bookItem") BookItem bookItem, Model model) throws DAOException {
        bookItemService.save(bookItem);

        getBooks(model);
        return "books.html";
    }

}
