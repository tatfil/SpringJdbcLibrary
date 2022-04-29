package com.example.controller;

import com.example.exception.DAOException;
import com.example.model.BookItem;
import com.example.service.BookItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookItemController {

    @Autowired
    private final BookItemService bookItemService;

    @Autowired
    public BookItemController(BookItemService bookItemService) {
        this.bookItemService = bookItemService;
    }

    @GetMapping("/books/booksList")
    public String getBooks(Model model) throws DAOException {

        List<BookItem> bookItems = bookItemService.findAll();
        model.addAttribute("bookPage", bookItems);
        return "books/booksList.html";
    }

    @GetMapping("/books//newBook")
    public String newBook(Model model) {
        model.addAttribute("bookItem", new BookItem());
        return "books/newBook";
    }

    @PostMapping("/newBook")
    public String newBook(@ModelAttribute("bookItem") BookItem bookItem, Model model) throws DAOException {
        Integer id = bookItemService.save(bookItem).getId();

        getBooks(model);
        return "redirect:/books/" + id;
    }

    /**
     * Custom handler for displaying an owner.
     *
     * @param bookId the ID of the owner to display
     * @return a ModelMap with the model attributes for the view
     */
//    @GetMapping("/books/{bookId}")
//    public ModelAndView showBook(@PathVariable("bookId") int bookId) throws DAOException {
//        var mav = new ModelAndView("bookDetails");
//        BookItem bookItem = bookItemService.findById(bookId).get();
//        mav.addObject(bookItem);
//        return mav;
//    }

    @GetMapping("/books/{bookId}")
    public String showBook(@PathVariable("bookId") int bookId, Model model) throws DAOException {

        BookItem bookItem = bookItemService.findById(bookId).get();
        model.addAttribute("bookItem", bookItem);
        return "books/bookDetails";
    }
}
