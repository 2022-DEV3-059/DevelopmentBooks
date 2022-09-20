package com.kata.shoppingbook.controller.book;


import com.kata.shoppingbook.dao.IBookDAO;
import com.kata.shoppingbook.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController implements IBookController {

    private IBookDAO bookDAO;

    public BookController(IBookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    @GetMapping
    public List<Book> getAllBook() {
        return bookDAO.findAll();
    }
}
