package com.kata.shoppingbook.model.cart.in;

import com.kata.shoppingbook.model.Book;

import java.util.Objects;

public class CartItem {
    private Book book;
    private Integer quantity;

    public CartItem(Book book, Integer quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public CartItem() {
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return book.equals(cartItem.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "book=" + book +
                ", quantity=" + quantity +
                '}';
    }
}
