package com.kata.shoppingbook.controller.cart;

import com.kata.shoppingbook.model.cart.in.CartIn;
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

    @Override
    @PostMapping
    public CartOut addToCart(@RequestBody  CartIn cartIn) {
        return cartService.addToCart(cartIn);
    }

    @Override
    @GetMapping("/{sessionToken}")
    public CartOut getCart(@PathVariable String sessionToken) {
        return cartService.getCart(sessionToken);
    }
}
