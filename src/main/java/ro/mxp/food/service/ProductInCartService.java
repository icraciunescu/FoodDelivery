package ro.mxp.food.service;

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

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CurrentUsername currentUsername;

    private ModelMapper modelMapper = new ModelMapper();

    private ProductInCartRepository productInCartRepository;
    @Autowired
    public ProductInCartService(ProductInCartRepository productInCartRepository) {
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

    public void addProductInCart(ProductInCartDto productInCartDto) {
        productInCartDto.setClient(clientRepository.findByUsername(currentUsername.displayCurrentUsername()));
        productInCartRepository.save((modelMapper.map(productInCartDto, ProductInCart.class)));
    }

    public void updateProductInCartService(Long id, int quantity) {
        Optional<ProductInCart> productInCart = productInCartRepository.findById(id);
        ProductInCart thisProductInCart = productInCart.get();

        if ((clientRepository.findByUsername(currentUsername.displayCurrentUsername()) != null)
                && clientRepository.findByUsername(currentUsername.displayCurrentUsername()).equals(thisProductInCart.getClient())) {
            productInCartRepository.updateProductInCartRepo(id, quantity);
        }

    }

    public void deleteProductInCart(Long id) {
        Optional<ProductInCart> productInCart = productInCartRepository.findById(id);
        ProductInCart thisProductInCart = productInCart.get();

        if ((clientRepository.findByUsername(currentUsername.displayCurrentUsername()) != null)
                && clientRepository.findByUsername(currentUsername.displayCurrentUsername()).equals(thisProductInCart.getClient())) {
            productInCartRepository.deleteById(id);
        }
    }

}
