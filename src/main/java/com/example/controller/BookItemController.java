package com.example.controller;

import com.example.exception.DAOException;
import com.example.model.BookItem;
import com.example.service.BookItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class BookItemController {
    private final BookItemService bookItemService;

    @Autowired
    public BookItemController(BookItemService bookItemService) {
        this.bookItemService = bookItemService;
    }

    @GetMapping("/books")
    public String getLessons(Model model) throws DAOException {

        List<BookItem> bookItems = bookItemService.findAll();
        model.addAttribute("bookPage", bookItems);
        return "books.html";
    }

}
