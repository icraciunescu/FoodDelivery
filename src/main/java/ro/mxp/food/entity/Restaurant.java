package ro.mxp.food.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Restaurant extends MyUser {

    private String restaurantName;
    private String restaurantSpecificity;
    private String restaurantAddress;
    private String restaurantPhone;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Product> productList;

    public Restaurant() {
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

}
