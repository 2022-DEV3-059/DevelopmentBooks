package com.kata.shoppingbook.model.cart.out;

import java.math.BigDecimal;
import java.util.Collection;

public class CartOut {
    private Collection<ItemWithDiscount> itemsAndDiscount;
    private BigDecimal totalPrice;
    private String sessionToken;

    private Integer cartCount;

    public CartOut() {
    }

    public CartOut(Collection<ItemWithDiscount> itemsAndDiscount, BigDecimal totalPrice, String sessionToken) {
        this.itemsAndDiscount = itemsAndDiscount;
        this.totalPrice = totalPrice;
        this.sessionToken = sessionToken;

        this.cartCount = itemsAndDiscount
                .stream()
                .mapToInt(i -> i.getBooks().size())
                .sum();
    }

    public Collection<ItemWithDiscount> getItemsAndDiscount() {
        return itemsAndDiscount;
    }

    public void setItemsAndDiscount(Collection<ItemWithDiscount> itemsAndDiscount) {
        this.itemsAndDiscount = itemsAndDiscount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public Integer getCartCount() {
        return cartCount;
    }

    @Override
    public String toString() {
        return "CartOut{" +
                "itemsAndDiscount=" + itemsAndDiscount +
                ", totalPrice=" + totalPrice +
                ", sessionToken='" + sessionToken + '\'' +
                '}';
    }
}
