package ro.mxp.food.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.CartDto;
import ro.mxp.food.entity.Cart;
import ro.mxp.food.repository.CartRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

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

        return cartDtoList;
    }

    public void addCartDto(CartDto cartDto) {
        cartRepository.save(modelMapper.map(cartDto, Cart.class));
    }


    public void updateCartService(Long id, int quantity) {
        cartRepository.updateCarRepo(id, quantity);
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

}
