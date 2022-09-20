package com.kata.shoppingbook.service.cart;

import com.kata.shoppingbook.model.Book;
import com.kata.shoppingbook.model.cart.in.CartIn;
import com.kata.shoppingbook.model.cart.in.CartItem;
import com.kata.shoppingbook.model.cart.out.CartOut;
import com.kata.shoppingbook.model.cart.out.ItemWithDiscount;
import com.kata.shoppingbook.service.discount.IDiscountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CartService implements ICartService {

    private static final Map<String, CartIn> USER_CART = new HashMap<>();

    private final IDiscountService discountService;

    public CartService(IDiscountService discountService) {
        this.discountService = discountService;
    }

    /**
     * Add item to the user cart
     *
     * @param cart
     * @return all items in the cart
     */
    @Override
    public CartOut addToCart(CartIn cart) {
        String sessionToken = cart.getSessionToken();

        if(USER_CART.get(sessionToken) == null) {
            USER_CART.put(sessionToken, cart);
        } else {
            appendCartForSession(cart);
        }

        return getCartOutByCartIn(USER_CART.get(sessionToken));
    }


    /**
     * Get all items in the user cart
     *
     * @param sessionToken the session token for user
     *
     * @return all items in the cart
     */
    @Override
    public CartOut getCart(String sessionToken) {
        if(USER_CART.get(sessionToken) != null) {
            return getCartOutByCartIn(USER_CART.get(sessionToken));
        }
        return new CartOut(Collections.emptyList(), BigDecimal.ZERO, sessionToken);
    }

    /**
     * Remove Item from user cart
     *
     * @param sessionToken
     * @param cartItem the item to be removed
     * @return all items in the cart
     */
    @Override
    public CartOut removeItem(String sessionToken, CartItem cartItem) {
        if(USER_CART.get(sessionToken) != null) {
            removeCartItemFromUserCartIn(sessionToken, cartItem);
        }
        return getCartOutByCartIn(USER_CART.get(sessionToken));
    }

    private void removeCartItemFromUserCartIn(String sessionToken, CartItem cartItem) {
        CartIn cartIn = USER_CART.get(sessionToken);
        Book book = cartItem.getBook();
        if(book == null) {
            return;
        }
        for(CartItem cart : cartIn.getCartItems()) {
            if(cart !=null && cart.getBook().getBookId() == book.getBookId()) {
                cart.setQuantity(cart.getQuantity() - 1 );
                if(cart.getQuantity() == 0) {
                    cartIn.removeCartItem(cart);
                }
                break;
            }
        }
    }

    private void appendCartForSession(CartIn cart){
        String session = cart.getSessionToken();
        List<CartItem> newItems = new ArrayList<>(cart.getCartItems());
        List<CartItem> existingItems = new ArrayList<>(USER_CART.get(session).getCartItems());
        existingItems.addAll(newItems);
        USER_CART.put(session, new CartIn(existingItems, session));
    }

    private CartOut getCartOutByCartIn(CartIn cartIn){
        List<ItemWithDiscount> itemsAndDiscount = discountService.getDiscountForCart(cartIn.getCartItems());

        BigDecimal totalPrice = itemsAndDiscount.stream()
                .map(ItemWithDiscount::getTotalPrice)
                .reduce(new BigDecimal(0), BigDecimal::add);

        return new CartOut(itemsAndDiscount, totalPrice, cartIn.getSessionToken());
    }
}
