package ro.mxp.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.mxp.food.dto.ProductInCartDto;
import ro.mxp.food.service.ProductInCartService;

import java.util.List;

@RestController
@RequestMapping("/product_in_cart")
public class ProductInCartController {

    private ProductInCartService productInCartService;

    @Autowired
    public ProductInCartController(ProductInCartService productInCartService) {
        this.productInCartService = productInCartService;
    }

    @GetMapping
    public List<ProductInCartDto> getAllProductInCartDto() {
        return productInCartService.getAllProductInCart();
    }

    @PostMapping
    public void addProductInCartDto(@RequestBody ProductInCartDto productInCartDto) {
        productInCartService.addProductInCartDto(productInCartDto);
    }

    @PutMapping("/{id}")
    public void updateProductInCartDto(@PathVariable Long id, @RequestParam int quantity) {
        productInCartService.updateProductInCartService(id, quantity);
    }

    @DeleteMapping("/{id}")
    public void deleteProductInCartDto(@PathVariable Long id) {
        productInCartService.deleteProductInCart(id);
    }

}
