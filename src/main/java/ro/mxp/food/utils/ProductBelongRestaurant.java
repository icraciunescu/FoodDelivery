package ro.mxp.food.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import ro.mxp.food.entity.Product;
import ro.mxp.food.entity.Restaurant;

@Component
public class ProductBelongRestaurant {

    public boolean productInRestaurant(@NotNull Restaurant restaurant, @NotNull Product product) {
        return restaurant.equals(product.getRestaurant());
    }

}
