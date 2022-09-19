package com.kata.shoppingbook.service.cart;

import com.kata.shoppingbook.model.cart.in.CartIn;
import com.kata.shoppingbook.model.cart.out.CartOut;

public interface ICartService {
    public CartOut addToCart(CartIn cart);
}
