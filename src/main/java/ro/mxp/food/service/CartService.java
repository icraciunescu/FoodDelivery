package ro.mxp.food.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.CartDto;
import ro.mxp.food.entity.Cart;
import ro.mxp.food.entity.Client;
import ro.mxp.food.repository.CartRepository;
import ro.mxp.food.repository.ClientRepository;
import ro.mxp.food.utils.CurrentUsername;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CurrentUsername currentUsername;

    private ModelMapper modelMapper = new ModelMapper();

    private CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<CartDto> getAllCart() {
        List<CartDto> cartDtoList = cartRepository.findAll()
                .stream()
                .map(cart -> modelMapper.map(cart, CartDto.class))
                .collect(Collectors.toList());

        if (clientRepository.findByUsername(currentUsername.displayCurrentUsername()) instanceof Client) {
            List<CartDto> cartDtoToClient = new LinkedList<>();
            for (CartDto cartDto : cartDtoList) {
                if (cartDto.getClient() != null && cartDto.getClient().equals(clientRepository.findByUsername(currentUsername.displayCurrentUsername()))) {
                    cartDtoToClient.add(cartDto);
                }
            }
            return cartDtoToClient;
        }

        return cartDtoList;
    }

    public void addCartDto(CartDto cartDto) {
        cartDto.setClient(clientRepository.findByUsername(currentUsername.displayCurrentUsername()));
        cartRepository.save(modelMapper.map(cartDto, Cart.class));
    }

    public void updateCartService(Long id, int quantity) {
        cartRepository.updateCartRepo(id, quantity);
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

}