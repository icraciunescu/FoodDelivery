package ro.mxp.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.mxp.food.dto.ProductInCartDto;
import ro.mxp.food.service.ProductInCartService;

import java.util.List;

@RestController
@RequestMapping("/product_in_cart")
public class ProductInCartController {

    private final ProductInCartService productInCartService;
    @Autowired
    public ProductInCartController(ProductInCartService productInCartService) {
        this.productInCartService = productInCartService;
    }

    @GetMapping
    public List<ProductInCartDto> getAllProductInCart() {
        return productInCartService.getAllProductInCart();
    }

    @PostMapping
    public void addProductInCart(@RequestBody ProductInCartDto productInCartDto) {
        productInCartService.addProductInCart(productInCartDto);
    }

    @PutMapping("/{id}")
    public void updateProductInCart(@PathVariable Long id, @RequestParam int quantity) {
        productInCartService.updateProductInCartService(id, quantity);
    }

    @DeleteMapping("/{id}")
    public void deleteProductInCart(@PathVariable Long id) {
        productInCartService.deleteProductInCart(id);
    }

}
