package com.kata.shoppingbook.service.utils;

import com.kata.shoppingbook.model.Author;
import com.kata.shoppingbook.model.Book;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PriceCalculatorTest {

    @Test
    void getTotalPriceWithEmptyListOfBook() {
        List<Book> books = Collections.emptyList();
        BigDecimal expectedPrice = new BigDecimal(0).setScale(2, RoundingMode.HALF_UP);
        double discount = 0d;
        BigDecimal actualPrice = PriceCalculator.getTotalPrice(books, discount);

        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    void getTotalPriceWithoutDiscountAnd1Book() {
        List<Book> books = Collections.singletonList(
                new Book(
                        1,
                        "Mock title 1",
                        2022,
                        new Author(1, "author 1"),
                        new BigDecimal(50),
                        null
                )
        );
        double discount = 0d;
        BigDecimal expectedPrice = new BigDecimal(50).setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualPrice = PriceCalculator.getTotalPrice(books, discount);

        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    void getTotalPriceWith3ItemsWith1TypeOfBook() {
        List<Book> books = Arrays.asList(
                new Book(
                        1,
                        "Mock title 1",
                        2022,
                        new Author(1, "author 1"),
                        new BigDecimal(50),
                        null
                ),
                new Book(
                        1,
                        "Mock title 1",
                        2022,
                        new Author(1, "author 1"),
                        new BigDecimal(50),
                        null
                ),
                new Book(
                        1,
                        "Mock title 1",
                        2022,
                        new Author(1, "author 1"),
                        new BigDecimal(50),
                        null
                )
        );
        double discount = 0d;
        BigDecimal expectedPrice = new BigDecimal(150).setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualPrice = PriceCalculator.getTotalPrice(books, discount);

        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    void getTotalPriceWithDiscount() {
        List<Book> books = Arrays.asList(
                new Book(
                        1,
                        "Mock title 1",
                        2022,
                        new Author(1, "author 1"),
                        new BigDecimal(50),
                        null
                ),
                new Book(
                        2,
                        "Mock title 2",
                        2022,
                        new Author(2, "author 1"),
                        new BigDecimal(50),
                        null
                )
        );
        double discount = 0.95; // 5%
        BigDecimal expectedPrice = new BigDecimal(95).setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualPrice = PriceCalculator.getTotalPrice(books, discount);

        assertEquals(expectedPrice, actualPrice);
    }


}