package com.kata.shoppingbook.service.utils;

import com.kata.shoppingbook.model.Book;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

public class PriceCalculator {

    /**
     * Calculate the total price of set of book
     * by adding the discount if the discount is not equal to 0
     *
     * @param books the book's collection
     * @param discount the discount
     * @return the total price
     */
    public static BigDecimal getTotalPrice(Collection<Book> books, double discount) {
        BigDecimal totalPriceWithoutDiscount =
                books.stream()
                        .map(Book::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (discount == 0d)
            return totalPriceWithoutDiscount.setScale(2, RoundingMode.HALF_UP);

        BigDecimal finalPrice = totalPriceWithoutDiscount.multiply(new BigDecimal(discount));
        return finalPrice.setScale(2, RoundingMode.HALF_UP);
    }
}
