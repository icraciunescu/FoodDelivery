package ro.mxp.food.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.ProductDto;
import ro.mxp.food.entity.Product;
import ro.mxp.food.entity.Restaurant;
import ro.mxp.food.repository.ProductRepository;
import ro.mxp.food.repository.RestaurantRepository;
import ro.mxp.food.utils.CurrentUsername;
import ro.mxp.food.utils.ProductBelongRestaurant;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private CurrentUsername currentUsername;
    @Autowired
    private ProductBelongRestaurant productBelongRestaurant;

    private ModelMapper modelMapper = new ModelMapper();

    private ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAllProduct() {
        List<ProductDto> productDtoList = productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());

        Restaurant restaurant = restaurantRepository.findByUsername(currentUsername.displayCurrentUsername());
        if (restaurant != null) {
            List<ProductDto> productsByRestaurant = new LinkedList<>();
            for (ProductDto productDto : productDtoList) {
                Product product = modelMapper.map(productDto, Product.class);
                if (productBelongRestaurant.productInRestaurant(restaurant, product)) {
                    productsByRestaurant.add(productDto);
                }
            }
            return productsByRestaurant;
        }

        return productDtoList;
    }

    public void addProduct(ProductDto productDto) {
        productDto.setRestaurant(restaurantRepository.findByUsername(currentUsername.displayCurrentUsername()));
        productRepository.save(modelMapper.map(productDto, Product.class));
    }

    public void updateProduct(Long id, String productName, String productType, Long productPrice, String productIngredients) {
        Optional<Product> product = productRepository.findById(id);
        Product thisProduct = product.get();
        Restaurant restaurant = restaurantRepository.findByUsername(currentUsername.displayCurrentUsername());
        if (productBelongRestaurant.productInRestaurant(restaurant, thisProduct)) {
            productRepository.updateProductRepo(id, productName, productType, productPrice, productIngredients);
        }
    }

    public void deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        Product thisProduct = product.get();
        Restaurant restaurant = restaurantRepository.findByUsername(currentUsername.displayCurrentUsername());
        if (productBelongRestaurant.productInRestaurant(restaurant, thisProduct)) {
            productRepository.deleteById(id);
        }
    }

}
