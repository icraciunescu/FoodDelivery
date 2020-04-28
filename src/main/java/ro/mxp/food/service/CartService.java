package ro.mxp.food.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.CartDto;
import ro.mxp.food.dto.PendingCartDto;
import ro.mxp.food.entity.*;
import ro.mxp.food.repository.CartRepository;
import ro.mxp.food.repository.ClientRepository;
import ro.mxp.food.repository.PendingCartRepository;
import ro.mxp.food.repository.ProductInCartRepository;
import ro.mxp.food.utils.CurrentUsername;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CurrentUsername currentUsername;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PendingCartRepository pendingCartRepository;
    @Autowired
    private ProductInCartRepository productInCartRepository;

    private ModelMapper modelMapper = new ModelMapper();

    private CartRepository cartRepository;
    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<CartDto> getAllCart() throws NullPointerException {
        List<Cart> cartList = new LinkedList<>();
        List<Cart> cartByClient = new LinkedList<>();
        try {
            for (Cart cart : cartRepository.findAll()) {
                if (clientRepository.findByUsername(currentUsername.displayCurrentUsername()).equals(cart.getProductInCartList().get(0).getClient())) {
                    cartByClient.add(cart);
                }
            }
            cartList.addAll(cartByClient);
        } catch (NullPointerException e) {
            e.getClass().getName();
        }
        return cartList
                .stream()
                .map(cart -> modelMapper.map(cart, CartDto.class))
                .collect(Collectors.toList());
    }

    public void addCart(CartDto cartDto) {
        List<ProductInCart> productInCartByClient = new LinkedList<>();
        long price = 0;
        for (ProductInCart productInCart : productInCartRepository.findAll()) {
            if (productInCart.getClient().getUsername().equals(currentUsername.displayCurrentUsername())) {
                price = price + productInCart.getProduct().getProductPrice() * productInCart.getQuantityProduct();
                productInCartByClient.add(productInCart);
            }
        }

        cartDto.setProductInCartList(productInCartByClient);
        cartDto.setValueCart(price);
        cartRepository.save(modelMapper.map(cartDto, Cart.class));
    }

    public void sendingCart(Long id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        Cart cart = optionalCart.get();

        Client client = cart.getProductInCartList().get(0).getClient();

        PendingCartDto pendingCartDto = new PendingCartDto();
        pendingCartDto.setValueCart(cart.getValueCart());
        pendingCartDto.setCart(cart);
        pendingCartDto.setClient(client);

        PendingCart pendingCart = modelMapper.map(pendingCartDto, PendingCart.class);

        if (pendingCart.getClient().equals(clientRepository.findByUsername(currentUsername.displayCurrentUsername()))) {
            pendingCartRepository.save(pendingCart);
            cartRepository.deleteById(id);
        }
    }

    public void deleteCart(Long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        Cart thisCart = cart.get();
        if (thisCart.getProductInCartList().get(0).getClient().equals(clientRepository.findByUsername(currentUsername.displayCurrentUsername()))) {
            cartRepository.deleteById(id);
        }

    }

}
