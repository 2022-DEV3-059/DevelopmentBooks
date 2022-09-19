package com.kata.shoppingbook.service.cart;

import com.kata.shoppingbook.model.Author;
import com.kata.shoppingbook.model.Book;
import com.kata.shoppingbook.model.cart.in.CartIn;
import com.kata.shoppingbook.model.cart.in.CartItem;
import com.kata.shoppingbook.model.cart.out.CartOut;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


public class CartServiceTest {

    private final CartService cartService = new CartService();

    @Test
    void addToCartTestFor1Call(){
        String sessionToken = UUID.randomUUID().toString();
        CartIn cart = new CartIn(Collections.singleton(
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
                )
        ), sessionToken);

        CartOut cartOut = cartService.addToCart(cart);

        assertNotNull(cartOut);
        assertNotNull(cartOut.getItemsAndDiscount());
        assertEquals(1, cartOut.getItemsAndDiscount().size());
        assertEquals(new BigDecimal(50).setScale(2, RoundingMode.HALF_UP), cartOut.getTotalPrice());
        assertEquals(sessionToken, cartOut.getSessionToken());

    }

    @Test
    void addToCartTestFor2CallWithSameSession(){
        String sessionToken = UUID.randomUUID().toString();

        CartIn cart1 = new CartIn(Collections.singleton(
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
                )
        ), sessionToken);

        cartService.addToCart(cart1);

        CartIn cart2 = new CartIn(Collections.singleton(
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
                )
        ), sessionToken);

        CartOut cartOut = cartService.addToCart(cart2);

        assertNotNull(cartOut);
        assertNotNull(cartOut.getItemsAndDiscount());
        assertEquals(2, cartOut.getItemsAndDiscount().size());
        assertEquals(new BigDecimal(100).setScale(2, RoundingMode.HALF_UP), cartOut.getTotalPrice());
        assertEquals(sessionToken, cartOut.getSessionToken());
    }

    @Test
    void addToCartTestFor2CallWithDifferentSession(){
        String sessionToken1 = UUID.randomUUID().toString();

        CartIn cart1 = new CartIn(Collections.singleton(
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
                )
        ), sessionToken1);

        CartOut cartOut1 = cartService.addToCart(cart1);

        assertNotNull(cartOut1);
        assertNotNull(cartOut1.getItemsAndDiscount());
        assertEquals(1, cartOut1.getItemsAndDiscount().size());
        assertEquals(new BigDecimal(50).setScale(2, RoundingMode.HALF_UP), cartOut1.getTotalPrice());
        assertEquals(sessionToken1, cartOut1.getSessionToken());

        String sessionToken2 = UUID.randomUUID().toString();

        CartIn cart2 = new CartIn(Collections.singleton(
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
                )
        ), sessionToken2);

        CartOut cartOut2 = cartService.addToCart(cart2);

        assertNotNull(cartOut2);
        assertNotNull(cartOut2.getItemsAndDiscount());
        assertEquals(1, cartOut2.getItemsAndDiscount().size());
        assertEquals(new BigDecimal(50).setScale(2, RoundingMode.HALF_UP), cartOut2.getTotalPrice());
        assertEquals(sessionToken2, cartOut2.getSessionToken());
    }


}
