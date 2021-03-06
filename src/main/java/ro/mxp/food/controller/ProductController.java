package ro.mxp.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.mxp.food.dto.ProductDto;
import ro.mxp.food.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getAllProduct() {
        return productService.getAllProduct();
    }

    @PostMapping
    public void addProduct(@RequestBody ProductDto productDto) {
        productService.addProduct(productDto);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Long id, @RequestParam String productName, @RequestParam String productType, @RequestParam Long productPrice,
                              @RequestParam String productIngredients) {
        productService.updateProduct(id, productName, productType, productPrice, productIngredients);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
