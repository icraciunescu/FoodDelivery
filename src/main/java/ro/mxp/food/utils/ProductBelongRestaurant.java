package ro.mxp.food.utils;

import org.springframework.stereotype.Service;
import ro.mxp.food.entity.Product;
import ro.mxp.food.entity.Restaurant;

@Service
public class ProductBelongRestaurant {

    public boolean productInRestaurant(Restaurant restaurant, Product product) {
        return restaurant.equals(product.getRestaurant());
    }

}
