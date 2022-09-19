package com.kata.shoppingbook.service.cart;

import com.kata.shoppingbook.model.Author;
import com.kata.shoppingbook.model.Book;
import com.kata.shoppingbook.model.cart.in.CartIn;
import com.kata.shoppingbook.model.cart.in.CartItem;
import com.kata.shoppingbook.model.cart.out.CartOut;
import com.kata.shoppingbook.model.cart.out.ItemWithDiscount;
import com.kata.shoppingbook.service.discount.IDiscountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
public class CartServiceTest {


    private final IDiscountService discountService = Mockito.mock(IDiscountService.class);
    private final CartService cartService = new CartService(discountService);


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

        List<ItemWithDiscount> itemWithDiscounts = Collections.singletonList(
                new ItemWithDiscount(
                        Collections.singleton(new Book(
                                1,
                                "Mock title 1",
                                2022,
                                new Author(1, "author 1"),
                                new BigDecimal(50),
                                null
                        )), 0d, new BigDecimal(50).setScale(2, RoundingMode.HALF_UP)
                )
        );
        Mockito.when(discountService.getDiscountForCart(Mockito.anyCollection()))
                .thenReturn(itemWithDiscounts);

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

        List<ItemWithDiscount> itemWithDiscounts = Collections.singletonList(
                new ItemWithDiscount(
                        Collections.singleton(new Book(
                                1,
                                "Mock title 1",
                                2022,
                                new Author(1, "author 1"),
                                new BigDecimal(50),
                                null
                        )), 0d, new BigDecimal(50).setScale(2, RoundingMode.HALF_UP)
                )
        );
        Mockito.when(discountService.getDiscountForCart(Mockito.anyCollection()))
                .thenReturn(itemWithDiscounts);

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

        List<ItemWithDiscount> itemWithDiscounts2 = Arrays.asList(
                new ItemWithDiscount(
                        Collections.singleton( new Book(
                                1,
                                "Mock title 1",
                                2022,
                                new Author(1, "author 1"),
                                new BigDecimal(50),
                                null
                        )), 0d, new BigDecimal(50).setScale(2, RoundingMode.HALF_UP)
                ),
                new ItemWithDiscount(
                        Collections.singleton( new Book(
                                1,
                                "Mock title 1",
                                2022,
                                new Author(1, "author 1"),
                                new BigDecimal(50),
                                null
                        )), 0d, new BigDecimal(50).setScale(2, RoundingMode.HALF_UP)
                )
        );
        Mockito.when(discountService.getDiscountForCart(Mockito.anyCollection()))
                .thenReturn(itemWithDiscounts2);

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

        List<ItemWithDiscount> itemWithDiscounts1 = Collections.singletonList(
                new ItemWithDiscount(
                        Collections.singleton(new Book(
                                1,
                                "Mock title 1",
                                2022,
                                new Author(1, "author 1"),
                                new BigDecimal(50),
                                null
                        )), 0d, new BigDecimal(50).setScale(2, RoundingMode.HALF_UP)
                )
        );
        Mockito.when(discountService.getDiscountForCart(Mockito.anyCollection()))
                .thenReturn(itemWithDiscounts1);

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

        List<ItemWithDiscount> itemWithDiscounts2 = Collections.singletonList(
                new ItemWithDiscount(
                        Collections.singleton(new Book(
                                1,
                                "Mock title 1",
                                2022,
                                new Author(1, "author 1"),
                                new BigDecimal(50),
                                null
                        )), 0d, new BigDecimal(50).setScale(2, RoundingMode.HALF_UP)
                )
        );
        Mockito.when(discountService.getDiscountForCart(cart2.getCartItems()))
                .thenReturn(itemWithDiscounts2);

        CartOut cartOut2 = cartService.addToCart(cart2);

        assertNotNull(cartOut2);
        assertNotNull(cartOut2.getItemsAndDiscount());
        assertEquals(1, cartOut2.getItemsAndDiscount().size());
        assertEquals(new BigDecimal(50).setScale(2, RoundingMode.HALF_UP), cartOut2.getTotalPrice());
        assertEquals(sessionToken2, cartOut2.getSessionToken());
    }


}
