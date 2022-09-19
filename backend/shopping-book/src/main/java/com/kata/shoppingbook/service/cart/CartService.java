package com.kata.shoppingbook.service.cart;

import com.kata.shoppingbook.model.cart.in.CartIn;
import com.kata.shoppingbook.model.cart.out.CartOut;
import com.kata.shoppingbook.service.discount.IDiscountService;

public class CartService implements ICartService {

    IDiscountService discountService;

    public CartService(IDiscountService discountService) {
        this.discountService = discountService;
    }

    @Override
    public CartOut addToCart(CartIn cart) {
        return null;
    }
}
