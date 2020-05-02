package ro.mxp.food.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.InProgressDto;
import ro.mxp.food.entity.InProgress;
import ro.mxp.food.entity.Product;
import ro.mxp.food.entity.Restaurant;
import ro.mxp.food.repository.InProgressRepository;
import ro.mxp.food.repository.RestaurantRepository;
import ro.mxp.food.utils.CurrentUsername;
import ro.mxp.food.utils.ProductBelongRestaurant;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InProgressService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private CurrentUsername currentUsername;
    @Autowired
    private ProductBelongRestaurant productBelongRestaurant;

    private final ModelMapper modelMapper = new ModelMapper();

    private final InProgressRepository inProgressRepository;
    @Autowired
    public InProgressService(InProgressRepository inProgressRepository) {
        this.inProgressRepository = inProgressRepository;
    }

    public List<InProgressDto> getAllInProgress() {
        List<InProgressDto> inProgressDtoList = inProgressRepository.findAll()
                .stream()
                .map(inProgress -> modelMapper.map(inProgress, InProgressDto.class))
                .collect(Collectors.toList());

        List<InProgressDto> inProgressDtoByRestaurant = new LinkedList<>();
        Restaurant restaurant = restaurantRepository.findByUsername(currentUsername.displayCurrentUsername());
        for (InProgressDto inProgressDto : inProgressDtoList) {
            Product product = inProgressDto.getCart().getProductInCartList().get(0).getProduct();
            if (productBelongRestaurant.productInRestaurant(restaurant, product)) {
                inProgressDtoByRestaurant.add(inProgressDto);
            }
        }

        return inProgressDtoByRestaurant;
    }

    public void deleteInProgress(Long id) {
        Optional<InProgress> inProgressDto = inProgressRepository.findById(id);
        InProgress inProgress = null;
        if (inProgressDto.isPresent()) {
            inProgress = inProgressDto.get();
        }

        Restaurant restaurant = restaurantRepository.findByUsername(currentUsername.displayCurrentUsername());
        assert inProgress != null;
        Product product = inProgress.getCart().getProductInCartList().get(0).getProduct();

        if (productBelongRestaurant.productInRestaurant(restaurant, product)) {
            inProgressRepository.deleteById(id);
        }
    }

}
