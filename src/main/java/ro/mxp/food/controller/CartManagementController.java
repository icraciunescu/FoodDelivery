package ro.mxp.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.mxp.food.service.CartManagementService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/management_controller")
public class CartManagementController {

    private CartManagementService cartManagementService;

    @Autowired
    public CartManagementController(CartManagementService cartManagementService) {
        this.cartManagementService = cartManagementService;
    }

    @GetMapping
    public Map<List<String>, Long> displayAllCart() {
        return cartManagementService.displayAllCart();
    }

}
