package ro.mxp.food.controller;

import org.springframework.web.bind.annotation.*;
import ro.mxp.food.dto.RestaurantDto;
import ro.mxp.food.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<RestaurantDto> getAllRestaurantDto() {
        return restaurantService.getAllRestaurant();
    }

    @PostMapping
    public void addRestaurantDto(@RequestBody RestaurantDto restaurantDto) {
        restaurantService.addRestaurant(restaurantDto);
    }

    @PutMapping("/{id}")
    public void updateRestaurantDto(@PathVariable Long id, @RequestParam String email, @RequestParam String username, @RequestParam String password,
            @RequestParam String restaurantName, @RequestParam String restaurantSpecificity, @RequestParam String restaurantAddress, @RequestParam String restaurantPhone) {
        restaurantService.updateRestaurant(id, email, username, password, restaurantName, restaurantSpecificity, restaurantAddress, restaurantPhone);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurantDto(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
    }


}
