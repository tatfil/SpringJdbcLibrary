package com.example.controller;

import com.example.model.BookItem;
import com.example.service.BookItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;


@WebMvcTest(controllers = BookItemController.class)
class BookItemControllerTest {

    @MockBean
    BookItemService bookItemService;

    @Autowired
    private MockMvc mvc;

    @Test
    void getGroupsTable_when1PageWith2Items() throws Exception {
        var book1 = new BookItem(111, "1", null, null, null);
        book1.setId(1);
        var book2 = new BookItem(222, "2", null, null, null);
        book2.setId(2);
        List<BookItem> bookItems = new ArrayList<>();
        bookItems.add(book1);
        bookItems.add(book2);

        when(bookItemService.findAll()).thenReturn( bookItems);

        mvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("books.html"))

                .andExpect(MockMvcResultMatchers.model().attributeExists("bookPage"))
                .andExpect(
                        MockMvcResultMatchers.model()
                                .attribute(
                                        "bookPage",
                                        hasItem(allOf(hasProperty("id", is(1)), hasProperty("title", is("1"))))))
                .andExpect(
                        MockMvcResultMatchers.model()
                                .attribute(
                                        "bookPage",
                                        hasItem(
                                                allOf(hasProperty("id", is(2)), hasProperty("title", is("2"))))));
    }

}