package ro.mxp.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.mxp.food.dto.PendingCartDto;
import ro.mxp.food.service.PendingCartService;

import java.util.List;

@RestController
@RequestMapping("/pending")
public class PendingCartController {

    private final PendingCartService pendingCartService;
    @Autowired
    public PendingCartController(PendingCartService pendingCartService) {
        this.pendingCartService = pendingCartService;
    }

    @GetMapping
    public List<PendingCartDto> getAllPendingCart() {
        return pendingCartService.getAllPendingCart();
    }

    @PatchMapping("/accept/{id}")
    public void inProgress(@PathVariable Long id) {
        pendingCartService.inProgress(id);
    }

    @DeleteMapping("/{id}")
    public void deletePendingCart(@PathVariable Long id) {
        pendingCartService.deletePendingCart(id);
    }

}
