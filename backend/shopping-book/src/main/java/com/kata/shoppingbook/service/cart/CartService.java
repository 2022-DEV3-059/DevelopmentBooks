package com.kata.shoppingbook.service.cart;

import com.kata.shoppingbook.model.cart.in.CartIn;
import com.kata.shoppingbook.model.cart.in.CartItem;
import com.kata.shoppingbook.model.cart.out.CartOut;
import com.kata.shoppingbook.model.cart.out.ItemWithDiscount;
import com.kata.shoppingbook.service.discount.IDiscountService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartService implements ICartService {

    private static final Map<String, CartIn> USER_CART = new HashMap<>();

    private final IDiscountService discountService;

    public CartService(IDiscountService discountService) {
        this.discountService = discountService;
    }

    @Override
    public CartOut addToCart(CartIn cart) {
        String sessionToken = cart.getSessionToken();


        if(USER_CART.get(sessionToken) == null) {
            USER_CART.put(sessionToken, cart);
        } else {
            appendCartForSession(cart);
        }

        List<ItemWithDiscount> itemsAndDiscount = discountService.getDiscountForCart(USER_CART.get(sessionToken).getCartItems());

        BigDecimal totalPrice = itemsAndDiscount.stream()
                .map(ItemWithDiscount::getTotalPrice)
                .reduce(new BigDecimal(0), BigDecimal::add);

        return new CartOut(itemsAndDiscount, totalPrice, sessionToken);
    }

    private void appendCartForSession(CartIn cart){
        String session = cart.getSessionToken();
        List<CartItem> newItems = new ArrayList<>(cart.getCartItems());
        List<CartItem> existingItems = new ArrayList<>(USER_CART.get(session).getCartItems());
        existingItems.addAll(newItems);
        USER_CART.put(session, new CartIn(existingItems, session));
    }
}
