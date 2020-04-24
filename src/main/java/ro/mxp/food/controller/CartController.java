package ro.mxp.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.mxp.food.dto.CartDto;
import ro.mxp.food.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/sorting")
    public List<CartDto> getAllCart() {
        return cartService.getAllCart();
    }

    @PostMapping
    public void addCart(@RequestBody CartDto cartDto) {
        cartService.addCart(cartDto);
    }

    @PatchMapping("/pending/{id}")
    public void pendingCart(@PathVariable Long id) {
        cartService.pendingCart(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
    }

}
