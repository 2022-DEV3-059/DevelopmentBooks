package com.kata.shoppingbook.service.discount;

import com.kata.shoppingbook.model.Author;
import com.kata.shoppingbook.model.Book;
import com.kata.shoppingbook.model.cart.in.CartItem;
import com.kata.shoppingbook.model.cart.out.ItemWithDiscount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiscountServiceTest {

    private final DiscountService discountService = new DiscountService();

    @Test
    void testDiscountWith5ItemsWith2DifferentBooks() {
        List<CartItem> itemsInCart = Arrays.asList(
                new CartItem(
                        new Book(
                                1,
                                "Mock title 1",
                                2022,
                                new Author(1, "author 1"),
                                new BigDecimal(50),
                                null
                        ),
                        3
                ),
                new CartItem(
                        new Book(
                                2,
                                "Mock title 2",
                                2022,
                                new Author(2, "author 2"),
                                new BigDecimal(50),
                                null
                        ),
                        2
                )
        );

        List<ItemWithDiscount> actualResult = discountService.getDiscountForCart(itemsInCart);
        assertEquals(3, actualResult.size());

        BigDecimal actualTotalPrice = actualResult.stream().map(ItemWithDiscount::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        assertEquals(new BigDecimal(240).setScale(2, RoundingMode.HALF_UP), actualTotalPrice);

        ItemWithDiscount itemWithDiscount1 = actualResult.get(0);
        assertEquals(2, itemWithDiscount1.getBooks().size());
        assertEquals(5d, itemWithDiscount1.getDiscount());
        assertEquals(new BigDecimal(95).setScale(2, RoundingMode.HALF_UP), itemWithDiscount1.getTotalPrice());

        ItemWithDiscount itemWithDiscount2 = actualResult.get(1);
        assertEquals(2, itemWithDiscount2.getBooks().size());
        assertEquals(5d, itemWithDiscount2.getDiscount());
        assertEquals(new BigDecimal(95).setScale(2, RoundingMode.HALF_UP), itemWithDiscount2.getTotalPrice());

        ItemWithDiscount itemWithDiscount3 = actualResult.get(2);
        assertEquals(1, itemWithDiscount3.getBooks().size());
        assertEquals(0d, itemWithDiscount3.getDiscount());
        assertEquals(new BigDecimal(50).setScale(2, RoundingMode.HALF_UP), itemWithDiscount3.getTotalPrice());

    }


    @Test
    void testDiscountWith5ItemsWith1DifferentBooks() {
        List<CartItem> itemsInCart = Collections.singletonList(
                new CartItem(
                        new Book(
                                1,
                                "Mock title 1",
                                2022,
                                new Author(1, "author 1"),
                                new BigDecimal(50),
                                null
                        ),
                        5
                )
        );

        List<ItemWithDiscount> actualResult = discountService.getDiscountForCart(itemsInCart);

        BigDecimal expectedTotalPrice = new BigDecimal(250).setScale(2, RoundingMode.HALF_UP);

        BigDecimal actualTotalProce = actualResult.stream().map(ItemWithDiscount::getTotalPrice)
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal(0));

        assertEquals(5, actualResult.size());
        assertEquals(expectedTotalPrice, actualTotalProce.setScale(2, RoundingMode.HALF_UP));

        ItemWithDiscount itemWithDiscount1 = actualResult.get(0);
        assertEquals(1, itemWithDiscount1.getBooks().size());
        assertEquals(0d, itemWithDiscount1.getDiscount());
        assertEquals(new BigDecimal(50).setScale(2, RoundingMode.HALF_UP), itemWithDiscount1.getTotalPrice());

        ItemWithDiscount itemWithDiscount2 = actualResult.get(0);
        assertEquals(1, itemWithDiscount2.getBooks().size());
        assertEquals(0d, itemWithDiscount2.getDiscount());
        assertEquals(new BigDecimal(50).setScale(2, RoundingMode.HALF_UP), itemWithDiscount2.getTotalPrice());

        ItemWithDiscount itemWithDiscount3 = actualResult.get(0);
        assertEquals(1, itemWithDiscount3.getBooks().size());
        assertEquals(0d, itemWithDiscount3.getDiscount());
        assertEquals(new BigDecimal(50).setScale(2, RoundingMode.HALF_UP), itemWithDiscount3.getTotalPrice());

        ItemWithDiscount itemWithDiscount4 = actualResult.get(0);
        assertEquals(1, itemWithDiscount4.getBooks().size());
        assertEquals(0d, itemWithDiscount4.getDiscount());
        assertEquals(new BigDecimal(50).setScale(2, RoundingMode.HALF_UP), itemWithDiscount4.getTotalPrice());

        ItemWithDiscount itemWithDiscount5 = actualResult.get(0);
        assertEquals(1, itemWithDiscount5.getBooks().size());
        assertEquals(0d, itemWithDiscount5.getDiscount());
        assertEquals(new BigDecimal(50).setScale(2, RoundingMode.HALF_UP), itemWithDiscount5.getTotalPrice());

    }

    @Test
    void testDiscountWith5Items5DifferentBooks(){
        List<CartItem> itemsInCart = Arrays.asList(
                new CartItem(
                        new Book(
                                1,
                                "Mock title 1",
                                2022,
                                new Author(1, "author 1"),
                                new BigDecimal(50),
                                null
                        ),
                        1
                ),
                new CartItem(
                        new Book(
                                2,
                                "Mock title 2",
                                2022,
                                new Author(2, "author 2"),
                                new BigDecimal(50),
                                null
                        ),
                        1
                ),
                new CartItem(
                        new Book(
                                3,
                                "Mock title 3",
                                2022,
                                new Author(3, "author 3"),
                                new BigDecimal(50),
                                null
                        ),
                        1
                ),
                new CartItem(
                        new Book(
                                4,
                                "Mock title 4",
                                2022,
                                new Author(4, "author 4"),
                                new BigDecimal(50),
                                null
                        ),
                        1
                ),
                new CartItem(
                        new Book(
                                5,
                                "Mock title 5",
                                2021,
                                new Author(5, "author 5"),
                                new BigDecimal(50),
                                null
                        ),
                        1
                )
        );

        List<ItemWithDiscount> actualResult = discountService.getDiscountForCart(itemsInCart);
        assertEquals(1, actualResult.size());


        BigDecimal actualTotalProce = actualResult.stream().map(ItemWithDiscount::getTotalPrice)
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal(0));

        assertEquals(new BigDecimal(187.5).setScale(2, RoundingMode.HALF_UP), actualTotalProce.setScale(2, RoundingMode.HALF_UP));

        ItemWithDiscount itemWithDiscount1 = actualResult.get(0);
        assertEquals(5, itemWithDiscount1.getBooks().size());
        assertEquals(25d, itemWithDiscount1.getDiscount());
        assertEquals(new BigDecimal(187.5).setScale(2, RoundingMode.HALF_UP), itemWithDiscount1.getTotalPrice());
    }
}