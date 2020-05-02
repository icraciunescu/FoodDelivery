package ro.mxp.food.service;

import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.RestaurantDto;
import ro.mxp.food.entity.Restaurant;
import ro.mxp.food.repository.MyUserRepository;
import ro.mxp.food.repository.RestaurantRepository;
import ro.mxp.food.utils.CurrentUsername;

import java.util.LinkedList;
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

    private final ModelMapper modelMapper = new ModelMapper();

    private final RestaurantRepository restaurantRepository;
    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<RestaurantDto> getAllRestaurant() {
        List<RestaurantDto> restaurantDtoList = new LinkedList<>();
        if (restaurantRepository.findByUsername(currentUsername.displayCurrentUsername()) == null) {
            List<RestaurantDto> allRestaurantDtoList = restaurantRepository.findAll()
                    .stream()
                    .map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class))
                    .collect(Collectors.toList());
            restaurantDtoList.addAll(allRestaurantDtoList);
        }

        if (restaurantRepository.findByUsername(currentUsername.displayCurrentUsername()) != null) {
            List<RestaurantDto> loginRestaurant = new LinkedList<>();
            Restaurant restaurant = restaurantRepository.findByUsername(currentUsername.displayCurrentUsername());
            loginRestaurant.add(modelMapper.map(restaurant, RestaurantDto.class));
            restaurantDtoList.addAll(loginRestaurant);
        }

        return restaurantDtoList;
    }

    public void addRestaurant(@NotNull RestaurantDto restaurantDto) {
        restaurantDto.setRole("RESTAURANT");
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
        if ((restaurant != null && restaurant.getId().equals(id)) || myUserRepository.findByUsername(currentUsername.displayCurrentUsername()) != null) {
            restaurantRepository.deleteById(id);
        }
    }

}
