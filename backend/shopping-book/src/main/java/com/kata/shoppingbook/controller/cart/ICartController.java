package com.kata.shoppingbook.controller.cart;

import com.kata.shoppingbook.model.cart.in.CartIn;
import com.kata.shoppingbook.model.cart.in.CartItem;
import com.kata.shoppingbook.model.cart.out.CartOut;

public interface ICartController {

    public CartOut addToCart(CartIn cartIn);

    public CartOut getCart(String sessionToken);

    public CartOut removeItem(String sessionToken, CartItem cartItem);
}
