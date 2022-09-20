package com.kata.shoppingbook.controller.book;

import com.kata.shoppingbook.model.Book;

import java.util.List;

public interface IBookController {

    /**
     * Rest api to get all books
     *
     * @return list of book
     */
    public List<Book> getAllBook();
}
