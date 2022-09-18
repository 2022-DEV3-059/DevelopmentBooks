package com.kata.shoppingbook.model.cart.in;

import java.util.Collection;

public class CartIn {
    private Collection<CartItem> cartItems;
    private String sessionToken;

    public CartIn() {
    }

    public CartIn(Collection<CartItem> cartItems, String sessionToken) {
        this.cartItems = cartItems;
        this.sessionToken = sessionToken;
    }

    public Collection<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Collection<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    @Override
    public String toString() {
        return "CartIn{" +
                "cartItems=" + cartItems +
                ", sessionToken='" + sessionToken + '\'' +
                '}';
    }
}
