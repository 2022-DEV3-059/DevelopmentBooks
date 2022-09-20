package com.kata.shoppingbook.controller.cart;

import com.kata.shoppingbook.model.cart.in.CartIn;
import com.kata.shoppingbook.model.cart.in.CartItem;
import com.kata.shoppingbook.model.cart.out.CartOut;

public interface ICartController {

    /**
     * Rest api to add items to cart
     *
     * @param cartIn
     * @return
     */
    public CartOut addToCart(CartIn cartIn);

    /**
     * Rest api to get all items from user cart
     *
     * @param sessionToken
     * @return
     */
    public CartOut getCart(String sessionToken);

    /**
     * Rest api to remove items to cart
     *
     * @param sessionToken
     * @param cartItem
     * @return
     */
    public CartOut removeItem(String sessionToken, CartItem cartItem);
}
