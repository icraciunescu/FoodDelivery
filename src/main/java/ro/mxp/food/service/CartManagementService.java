package ro.mxp.food.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.mxp.food.entity.Cart;
import ro.mxp.food.repository.CartRepository;
import ro.mxp.food.repository.ClientRepository;
import ro.mxp.food.utils.CurrentUsername;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class CartManagementService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CurrentUsername currentUsername;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    public CartManagementService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Map<List<String>, Long> displayAllCart() {

        String usernameClient = clientRepository.findByUsername(currentUsername.displayCurrentUsername()).getUsername();

        List<Cart> cartList = cartRepository.findAll();
        List<String> productList = new LinkedList<>();
        long price = 0L;

        for (Cart cart : cartList) {
            if (cart.getClient().getUsername().equals(usernameClient)) {
                productList.add(cart.getProduct().getProductName());
                price = price + cart.getProduct().getProductPrice()*cart.getQuantityProduct();
            }
        }

        Map<List<String>, Long> listProductAndPriceMap = new HashMap<>();
        listProductAndPriceMap.put(productList, price);

        return listProductAndPriceMap;
    }

}
