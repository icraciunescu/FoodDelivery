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

    @GetMapping("/pending")
    public List<CartDto> getAllCartDto() {
        return cartService.getAllCartDto();
    }

    @PostMapping("/send_command")
    public void addCartDto(@RequestBody CartDto cartDto) {
        cartService.addCart(cartDto);
    }

}
