package ro.mxp.food.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.InProgressDto;
import ro.mxp.food.dto.PendingCartDto;
import ro.mxp.food.entity.Client;
import ro.mxp.food.entity.InProgress;
import ro.mxp.food.entity.PendingCart;
import ro.mxp.food.repository.ClientRepository;
import ro.mxp.food.repository.InProgressRepository;
import ro.mxp.food.repository.PendingCartRepository;
import ro.mxp.food.repository.RestaurantRepository;
import ro.mxp.food.utils.CurrentUsername;

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

        if (clientRepository.findByUsername(currentUsername.displayCurrentUsername()) != null) {
            for (PendingCartDto pendingCartDto : pendingCartDtoList) {
                if (pendingCartDto.getClient().equals(clientRepository.findByUsername(currentUsername.displayCurrentUsername()))) {
                    pendingCartDtoListReturn.add(pendingCartDto);
                }
            }
        }

        if (restaurantRepository.findByUsername(currentUsername.displayCurrentUsername()) != null) {
            for (PendingCartDto pendingCartDto : pendingCartDtoList) {
                    pendingCartDtoListReturn.add(pendingCartDto);
                }
        }

        return pendingCartDtoListReturn;
    }

    public void inProgress(Long id) {
        Optional<PendingCart> optionalPendingCart = pendingCartRepository.findById(id);
        PendingCart pendingCart = optionalPendingCart.get();

        InProgressDto inProgressDto = modelMapper.map(pendingCart, InProgressDto.class);
        InProgress inProgress = modelMapper.map(inProgressDto, InProgress.class);
        inProgressRepository.save(inProgress);
        pendingCartRepository.deleteById(id);
    }

    public void deletePendingCart(Long id) {
        Optional<PendingCart> optionalPendingCart = pendingCartRepository.findById(id);
        PendingCart pendingCart = optionalPendingCart.get();

        Client client = pendingCart.getClient();

        if ((clientRepository.findByUsername((currentUsername.displayCurrentUsername())) != null && clientRepository.findByUsername((currentUsername.displayCurrentUsername())).equals(client))) {
            pendingCartRepository.deleteById(id);
        }
    }

}
