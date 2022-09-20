package com.kata.shoppingbook.service.cart;

import com.kata.shoppingbook.model.cart.in.CartIn;
import com.kata.shoppingbook.model.cart.in.CartItem;
import com.kata.shoppingbook.model.cart.out.CartOut;

public interface ICartService {

    /**
     * Add item to the user cart
     *
     * @param cart
     * @return all items in the cart
     */
    public CartOut addToCart(CartIn cart);

    /**
     * Get all items in the user cart
     *
     * @param sessionToken the session token for user
     *
     * @return all items in the cart
     */
    public CartOut getCart(String sessionToken);

    /**
     * Remove Item from user cart
     *
     * @param sessionToken
     * @param cartItem the item to be removed
     * @return all items in the cart
     */
    public CartOut removeItem(String sessionToken, CartItem cartItem);
}
