package ro.mxp.food.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.RestaurantDto;
import ro.mxp.food.entity.Restaurant;
import ro.mxp.food.repository.RestaurantRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private BCryptPasswordEncoder getBCryptPasswordEncoder;

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
        restaurantRepository.updateRestaurantRepo(id, email, username, restaurantName, restaurantSpecificity, restaurantAddress, restaurantPhone);
    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

}
