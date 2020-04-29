package ro.mxp.food.dto;

public class RestaurantDto extends MyUserDto {

    private String restaurantName;
    private String restaurantSpecificity;
    private String restaurantAddress;
    private String restaurantPhone;

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

}
