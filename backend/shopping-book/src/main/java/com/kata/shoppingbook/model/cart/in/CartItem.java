package com.kata.shoppingbook.model.cart.in;

import com.kata.shoppingbook.model.Book;

public class CartItem {
    private Book book;
    private Integer quantity;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "book=" + book +
                ", quantity=" + quantity +
                '}';
    }
}
