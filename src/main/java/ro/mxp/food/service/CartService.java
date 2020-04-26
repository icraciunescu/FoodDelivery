package ro.mxp.food.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.CartDto;
import ro.mxp.food.dto.PendingCartDto;
import ro.mxp.food.entity.Cart;
import ro.mxp.food.entity.PendingCart;
import ro.mxp.food.entity.ProductInCart;
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
    private ProductInCartRepository productInCartRepository;
    @Autowired
    private CurrentUsername currentUsername;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PendingCartRepository pendingCartRepository;

    private ModelMapper modelMapper = new ModelMapper();

    private CartRepository cartRepository;
    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<CartDto> getAllCart() {
        List<Cart> cartList = new LinkedList<>();
            List<Cart> cartByClient = new LinkedList<>();
            for (Cart cart : cartRepository.findAll()) {
                if (cart.getProductInCartList().size() > 0) {
                    if (clientRepository.findByUsername(currentUsername.displayCurrentUsername()).equals(cart.getProductInCartList().get(0).getClient())) {
                        cartByClient.add(cart);
                    }
                }
            }
            cartList.addAll(cartByClient);
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
        long price = 0;
        for (ProductInCart productInCart : productInCartByClient) {
            if (productInCart.getProduct().getRestaurant().equals(productInCartByClient.get(0).getProduct().getRestaurant())) {
                productInCartByClientAndRestaurant.add(productInCart);
                price = price + productInCart.getProduct().getProductPrice() * productInCart.getQuantityProduct();
            }
        }
        cartDto.setProductInCartList(productInCartByClientAndRestaurant);
        cartDto.setValueCart(price);
        cartRepository.save(modelMapper.map(cartDto, Cart.class));
    }

    public void sendingCart(Long id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        Cart cart =  optionalCart.get();
        PendingCartDto pendingCartDto = modelMapper.map(cart, PendingCartDto.class);
        PendingCart pendingCart = modelMapper.map(pendingCartDto, PendingCart.class);
        pendingCartRepository.save(pendingCart);

        cartRepository.deleteById(id);
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

}
