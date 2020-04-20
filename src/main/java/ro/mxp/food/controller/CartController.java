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

    @GetMapping
    public List<CartDto> getAllCartDto() {
        return cartService.getAllCart();
    }

    @PostMapping
    public void addCartDto(@RequestBody CartDto cartDto) {
        cartService.addCartDto(cartDto);
    }

    @PutMapping("/{id}")
    public void updateCartDto(@PathVariable Long id, @RequestParam int quantity) {
        cartService.updateCartService(id, quantity);
    }

    @DeleteMapping("/{id}")
    public void deleteCartDto(@PathVariable Long id) {
        cartService.deleteCart(id);
    }

}
