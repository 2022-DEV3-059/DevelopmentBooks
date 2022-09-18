package com.kata.shoppingbook.service.discount;

import com.kata.shoppingbook.model.cart.in.CartItem;
import com.kata.shoppingbook.model.cart.out.ItemWithDiscount;

import java.util.Collection;
import java.util.List;

public interface IDiscountService {
    public List<ItemWithDiscount> getDiscountForCart(Collection<CartItem> cartItems);
}
