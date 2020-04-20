package ro.mxp.food.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.RestaurantDto;
import ro.mxp.food.entity.MyUser;
import ro.mxp.food.entity.Restaurant;
import ro.mxp.food.repository.MyUserRepository;
import ro.mxp.food.repository.RestaurantRepository;
import ro.mxp.food.utils.CurrentUsername;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private BCryptPasswordEncoder getBCryptPasswordEncoder;

    @Autowired
    private CurrentUsername currentUsername;

    @Autowired
    private MyUserRepository myUserRepository;

    private ModelMapper modelMapper = new ModelMapper();

    private RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<RestaurantDto> getAllRestaurant() {
        return restaurantRepository.findAll()
                .stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class))
                .collect(Collectors.toList());
    }

    public void addRestaurant(RestaurantDto restaurantDto) {
        restaurantDto.setPassword(getBCryptPasswordEncoder.encode(restaurantDto.getPassword()));
        restaurantRepository.save(modelMapper.map(restaurantDto, Restaurant.class));
    }

    public void updateRestaurant(Long id, String email, String username, String restaurantName, String restaurantSpecificity, String restaurantAddress, String restaurantPhone) {
        Restaurant restaurant = restaurantRepository.findByUsername(currentUsername.displayCurrentUsername());
        if (restaurant != null && restaurant.getId().equals(id)) {
            restaurantRepository.updateRestaurantRepo(id, email, username, restaurantName, restaurantSpecificity, restaurantAddress, restaurantPhone);
        }
    }

    public void deleteRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findByUsername(currentUsername.displayCurrentUsername());
        if ((restaurant != null && restaurant.getId().equals(id)) || myUserRepository.findByUsername(currentUsername.displayCurrentUsername()) instanceof MyUser){
            restaurantRepository.deleteById(id);
        }
    }

}
