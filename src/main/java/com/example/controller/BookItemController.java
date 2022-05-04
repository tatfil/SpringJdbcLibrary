package com.example.controller;

import com.example.exception.DAOException;
import com.example.model.Author;
import com.example.model.BookItem;
import com.example.model.BookItemDTO;
import com.example.model.Patron;
import com.example.service.AuthorService;
import com.example.service.BookItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BookItemController {

    @Autowired
    private final BookItemService bookItemService;
    @Autowired
    private final AuthorService authorService;

    @Autowired
    public BookItemController(BookItemService bookItemService, AuthorService authorService) {
        this.bookItemService = bookItemService;
        this.authorService = authorService;
    }

    @GetMapping("/books/booksList")
    public String getBooks(Model model) throws DAOException {

        List<BookItem> bookItems = bookItemService.findAll();
        List<BookItemDTO> dto = new ArrayList<>();
        for(BookItem book: bookItems){
            dto.add(bookItemService.getBookItemDto(book));
        }
        model.addAttribute("bookPage", dto);
        return "books/booksList.html";
    }

    @GetMapping("/books//newBook")
    public String newBook(Model model) {
        model.addAttribute("bookItem", new BookItem());
        model.addAttribute("author", new Author());
        return "books/newBook";
    }

    @PostMapping("/newBook")
    public String newBook(@ModelAttribute("bookItem") BookItem bookItem, @ModelAttribute("author") Author author, Model model) throws DAOException {

        Integer id = bookItemService.save(new BookItem(bookItem.getIsbn(), bookItem.getTitle(), bookItem.getBarcode(),
                bookItem.getStatus(), bookItem.getBorrowed())).getId();

        Optional<Author> authorDb = authorService.findByName(author.getName());

        if(authorDb.isEmpty()){
            Author newAuthor = authorService.save(author);
            bookItemService.addBookToAuthor(id, newAuthor.getId());
        }
        else{
            bookItemService.addBookToAuthor(id, authorDb.get().getId());
        }
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
        BookItemDTO dto = bookItemService.getBookItemDto(bookItem);

        model.addAttribute("bookItemDTO", dto);
        return "books/bookDetails";
    }
}
