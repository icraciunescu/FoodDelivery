package ro.mxp.food.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.CartDto;
import ro.mxp.food.entity.Cart;
import ro.mxp.food.entity.Client;
import ro.mxp.food.entity.ProductInCart;
import ro.mxp.food.entity.Restaurant;
import ro.mxp.food.repository.CartRepository;
import ro.mxp.food.repository.ClientRepository;
import ro.mxp.food.repository.ProductInCartRepository;
import ro.mxp.food.repository.RestaurantRepository;
import ro.mxp.food.utils.CurrentUsername;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private ProductInCartRepository productInCartRepository;
    @Autowired
    private CurrentUsername currentUsername;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    private ModelMapper modelMapper = new ModelMapper();

    private CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<CartDto> getAllCartDto() {
        List<Cart> cartList = new LinkedList<>();
        if (clientRepository.findByUsername(currentUsername.displayCurrentUsername()) instanceof Client) {
            List<Cart> cartByClient = new LinkedList<>();
            for (Cart cart : cartRepository.findAll()) {
                if (cart.getProductInCartList().size() > 0) {
                    if (clientRepository.findByUsername(currentUsername.displayCurrentUsername()).equals(cart.getProductInCartList().get(0).getClient())) {
                        cartByClient.add(cart);
                    }
                }
            }
            cartList.addAll(cartByClient);
        }
        if (restaurantRepository.findByUsername(currentUsername.displayCurrentUsername()) instanceof Restaurant) {
            List<Cart> cartByRestaurant = new LinkedList<>();
            for (Cart cart : cartRepository.findAll()) {
                if (cart.getProductInCartList().size() > 0) {
                    if (restaurantRepository.findByUsername(currentUsername.displayCurrentUsername()).equals(cart.getProductInCartList().get(0).getProduct().getRestaurant())) {
                        cartByRestaurant.add(cart);
                    }
                }
            }
            cartList.addAll(cartByRestaurant);
        }
        return cartList
                .stream()
                .map(cart -> modelMapper.map(cart, CartDto.class))
                .collect(Collectors.toList());

    }

    public void addCart(CartDto cartDto) {
        List<ProductInCart> productInCartByClient = new LinkedList<>();
        for (ProductInCart productInCart : productInCartRepository.findAll()) {
            if (productInCart.getClient().getUsername().equals(currentUsername.displayCurrentUsername())) {
                productInCartByClient.add(productInCart);
            }
        }
        List<ProductInCart> productInCartByClientAndRestaurant = new LinkedList<>();
        for (ProductInCart productInCart : productInCartByClient) {
            if (productInCart.getProduct().getRestaurant().equals(productInCartByClient.get(0).getProduct().getRestaurant())) {
                productInCartByClientAndRestaurant.add(productInCart);
            }
        }
        cartDto.setProductInCartList(productInCartByClientAndRestaurant);
        cartRepository.save(modelMapper.map(cartDto, Cart.class));
    }

}
