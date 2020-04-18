package ro.mxp.food.controller;

import org.springframework.web.bind.annotation.*;
import ro.mxp.food.dto.ProductDto;
import ro.mxp.food.entity.Restaurant;
import ro.mxp.food.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getAllProductDto() {
        return productService.getAllProduct();
    }

    @PostMapping
    public void addProductDto(@RequestBody ProductDto productDto) {
        productService.addProduct(productDto);
    }

    @PutMapping("/{id}")
    public void updateProductDto(@PathVariable Long id, @RequestParam String productName, @RequestParam String productType, @RequestParam Long productPrice,
                                 @RequestParam String productIngredients) {
        productService.updateProduct(id, productName, productType, productPrice, productIngredients);
    }

    @DeleteMapping("/{id}")
    public void deleteProductDto(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
