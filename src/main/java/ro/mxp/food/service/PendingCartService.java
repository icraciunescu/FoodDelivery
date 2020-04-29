package ro.mxp.food.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.InProgressDto;
import ro.mxp.food.dto.PendingCartDto;
import ro.mxp.food.entity.*;
import ro.mxp.food.repository.ClientRepository;
import ro.mxp.food.repository.InProgressRepository;
import ro.mxp.food.repository.PendingCartRepository;
import ro.mxp.food.repository.RestaurantRepository;
import ro.mxp.food.utils.CurrentUsername;
import ro.mxp.food.utils.ProductBelongRestaurant;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PendingCartService {

    @Autowired
    private InProgressRepository inProgressRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private CurrentUsername currentUsername;
    @Autowired
    private ProductBelongRestaurant productBelongRestaurant;

    private ModelMapper modelMapper = new ModelMapper();

    private PendingCartRepository pendingCartRepository;

    @Autowired
    public PendingCartService(PendingCartRepository pendingCartRepository) {
        this.pendingCartRepository = pendingCartRepository;
    }

    public List<PendingCartDto> getAllPendingCart() {
        List<PendingCartDto> pendingCartDtoList = pendingCartRepository.findAll()
                .stream()
                .map(productForDelivery -> modelMapper.map(productForDelivery, PendingCartDto.class))
                .collect(Collectors.toList());
        List<PendingCartDto> pendingCartDtoListReturn = new LinkedList<>();

        Client client = clientRepository.findByUsername(currentUsername.displayCurrentUsername());
        if (client != null) {
            for (PendingCartDto pendingCartDto : pendingCartDtoList) {
                if (pendingCartDto.getClient().equals(client)) {
                    pendingCartDtoListReturn.add(pendingCartDto);
                }
            }
        }

        Restaurant restaurant = restaurantRepository.findByUsername(currentUsername.displayCurrentUsername());
        if (restaurant != null) {
            for (PendingCartDto pendingCartDto : pendingCartDtoList) {
                Product product = pendingCartDto.getCart().getProductInCartList().get(0).getProduct();
                if (productBelongRestaurant.productInRestaurant(restaurant, product)) {
                    pendingCartDtoListReturn.add(pendingCartDto);
                }
            }
        }

        return pendingCartDtoListReturn;
    }

    public void inProgress(Long id) {
        Optional<PendingCart> optionalPendingCart = pendingCartRepository.findById(id);
        PendingCart pendingCart = optionalPendingCart.get();

        Restaurant restaurant = pendingCart.getCart().getProductInCartList().get(0).getProduct().getRestaurant();
        Product product = pendingCart.getCart().getProductInCartList().get(0).getProduct();

        if (productBelongRestaurant.productInRestaurant(restaurant, product)) {
            InProgressDto inProgressDto = modelMapper.map(pendingCart, InProgressDto.class);
            InProgress inProgress = modelMapper.map(inProgressDto, InProgress.class);
            inProgressRepository.save(inProgress);
            pendingCartRepository.deleteById(id);
        }
    }

    public void deletePendingCart(Long id) {
        Optional<PendingCart> optionalPendingCart = pendingCartRepository.findById(id);
        PendingCart pendingCart = optionalPendingCart.get();

        Restaurant restaurant = pendingCart.getCart().getProductInCartList().get(0).getProduct().getRestaurant();
        Client client = pendingCart.getClient();

        if ((restaurantRepository.findByUsername(currentUsername.displayCurrentUsername()) != null && restaurantRepository.findByUsername(currentUsername.displayCurrentUsername()).equals(restaurant))
                || (clientRepository.findByUsername((currentUsername.displayCurrentUsername())) != null && clientRepository.findByUsername((currentUsername.displayCurrentUsername())).equals(client))) {
            pendingCartRepository.deleteById(id);
        }
    }

}
