package ro.mxp.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.mxp.food.dto.ProductForDeliveryDto;
import ro.mxp.food.service.ProductForDeliveryService;

import java.util.List;

@RestController
@RequestMapping("/product_for_delivery")
public class ProductForDeliveryController {

    private ProductForDeliveryService productForDeliveryService;
    @Autowired
    public ProductForDeliveryController(ProductForDeliveryService productForDeliveryService) {
        this.productForDeliveryService = productForDeliveryService;
    }

    @GetMapping
    public List<ProductForDeliveryDto> getAllProductForDelivery() {
        return productForDeliveryService.getAllProductForDelivery();
    }



}
