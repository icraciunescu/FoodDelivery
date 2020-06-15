package ro.mxp.food.service;

import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.ProductInCartDto;
import ro.mxp.food.entity.ProductInCart;
import ro.mxp.food.repository.ClientRepository;
import ro.mxp.food.repository.ProductInCartRepository;
import ro.mxp.food.utils.CurrentUsername;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductInCartService {

    private final ClientRepository clientRepository;
    private final CurrentUsername currentUsername;
    private final ModelMapper modelMapper = new ModelMapper();
    private final ProductInCartRepository productInCartRepository;

    @Autowired
    public ProductInCartService(ClientRepository clientRepository, CurrentUsername currentUsername, ProductInCartRepository productInCartRepository) {
        this.clientRepository = clientRepository;
        this.currentUsername = currentUsername;
        this.productInCartRepository = productInCartRepository;
    }

    public List<ProductInCartDto> getAllProductInCart() {
        List<ProductInCartDto> productInCartDtoList = productInCartRepository.findAll()
                .stream()
                .map(cart -> modelMapper.map(cart, ProductInCartDto.class))
                .collect(Collectors.toList());

        if (clientRepository.findByUsername(currentUsername.displayCurrentUsername()) != null) {
            List<ProductInCartDto> productInCartDtoByClient = new LinkedList<>();
            for (ProductInCartDto productInCartDto : productInCartDtoList) {
                if (productInCartDto.getClient() != null && productInCartDto.getClient().equals(clientRepository.findByUsername(currentUsername.displayCurrentUsername()))) {
                    productInCartDtoByClient.add(productInCartDto);
                }
            }
            return productInCartDtoByClient;
        }

        return productInCartDtoList;
    }

    public void addProductInCart(@NotNull ProductInCartDto productInCartDto) {
        productInCartDto.setClient(clientRepository.findByUsername(currentUsername.displayCurrentUsername()));
        productInCartRepository.save((modelMapper.map(productInCartDto, ProductInCart.class)));
    }

    public void updateProductInCartService(Long id, int quantity) {
        ProductInCart productInCart = findProductInCart(id);
        if ((clientRepository.findByUsername(currentUsername.displayCurrentUsername()) != null)
                && clientRepository.findByUsername(currentUsername.displayCurrentUsername()).equals(productInCart.getClient())) {
            productInCartRepository.updateProductInCart(id, quantity);
        }
    }

    public void deleteProductInCart(Long id) {
        ProductInCart productInCart = findProductInCart(id);
        if ((clientRepository.findByUsername(currentUsername.displayCurrentUsername()) != null)
                && clientRepository.findByUsername(currentUsername.displayCurrentUsername()).equals(productInCart.getClient())) {
            productInCartRepository.deleteById(id);
        }
    }

    protected ProductInCart findProductInCart(Long id) {
        Optional<ProductInCart> productInCart = productInCartRepository.findById(id);
        ProductInCart thisProductInCart = null;
        if (productInCart.isPresent()) {
            thisProductInCart = productInCart.get();
        }
        return thisProductInCart;
    }

}
