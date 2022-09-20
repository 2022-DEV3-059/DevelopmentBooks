package com.kata.shoppingbook.service.cart;

import com.kata.shoppingbook.model.cart.in.CartIn;
import com.kata.shoppingbook.model.cart.in.CartItem;
import com.kata.shoppingbook.model.cart.out.CartOut;

public interface ICartService {
    public CartOut addToCart(CartIn cart);

    public CartOut getCart(String sessionToken);

    public CartOut removeItem(String sessionToken, CartItem cartItem);
}
