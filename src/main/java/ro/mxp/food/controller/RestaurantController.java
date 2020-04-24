package ro.mxp.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.mxp.food.dto.RestaurantDto;
import ro.mxp.food.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private RestaurantService restaurantService;
    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<RestaurantDto> getAllRestaurant() {
        return restaurantService.getAllRestaurant();
    }

    @PostMapping
    public void addRestaurant(@RequestBody RestaurantDto restaurantDto) {
        restaurantService.addRestaurant(restaurantDto);
    }

    @PutMapping("/{id}")
    public void updateRestaurant(@PathVariable Long id, @RequestParam String email, @RequestParam String username,
                                 @RequestParam String restaurantName, @RequestParam String restaurantSpecificity, @RequestParam String restaurantAddress, @RequestParam String restaurantPhone) {
        restaurantService.updateRestaurant(id, email, username, restaurantName, restaurantSpecificity, restaurantAddress, restaurantPhone);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
    }


}
