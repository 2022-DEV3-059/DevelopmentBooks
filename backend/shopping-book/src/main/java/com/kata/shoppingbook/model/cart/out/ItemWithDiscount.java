package com.kata.shoppingbook.model.cart.out;

import com.kata.shoppingbook.model.Book;

import java.math.BigDecimal;
import java.util.Collection;

public class ItemWithDiscount {
    private Collection<Book> books;
    private double discount;
    private BigDecimal totalPrice;

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "ItemWithDiscount{" +
                "books=" + books +
                ", discount=" + discount +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
