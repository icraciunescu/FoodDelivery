package ro.mxp.food.dto;

import ro.mxp.food.entity.Product;

import java.util.List;

public class RestaurantDto extends MyUserDto {

    private String restaurantName;
    private String restaurantSpecificity;
    private String restaurantAddress;
    private String restaurantPhone;
    private List<Product> productList;

    public RestaurantDto() {
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantSpecificity() {
        return restaurantSpecificity;
    }

    public void setRestaurantSpecificity(String restaurantSpecificity) {
        this.restaurantSpecificity = restaurantSpecificity;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantPhone() {
        return restaurantPhone;
    }

    public void setRestaurantPhone(String restaurantPhone) {
        this.restaurantPhone = restaurantPhone;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
    
}
