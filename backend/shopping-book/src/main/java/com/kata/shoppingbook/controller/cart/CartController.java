package com.kata.shoppingbook.controller.cart;

import com.kata.shoppingbook.model.cart.in.CartIn;
import com.kata.shoppingbook.model.cart.in.CartItem;
import com.kata.shoppingbook.model.cart.out.CartOut;
import com.kata.shoppingbook.service.cart.ICartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cart")
public class CartController implements ICartController {

    private ICartService cartService;


    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    /**
     * Rest api to add items to cart
     *
     * @param cartIn
     * @return
     */
    @Override
    @PostMapping
    public CartOut addToCart(@RequestBody  CartIn cartIn) {
        return cartService.addToCart(cartIn);
    }

    /**
     * Rest api to get all items from user cart
     *
     * @param sessionToken
     * @return
     */
    @Override
    @GetMapping("/{sessionToken}")
    public CartOut getCart(@PathVariable String sessionToken) {
        return cartService.getCart(sessionToken);
    }

    /**
     * Rest api to remove items to cart
     *
     * @param sessionToken
     * @param cartItem
     * @return
     */
    @Override
    @DeleteMapping("/{sessionToken}")
    public CartOut removeItem(@PathVariable String sessionToken, @RequestBody CartItem cartItem) {
        return cartService.removeItem(sessionToken, cartItem);
    }
}
