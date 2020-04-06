package ro.mxp.food.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.ProductDto;
import ro.mxp.food.entity.Product;
import ro.mxp.food.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ModelMapper modelMapper = new ModelMapper();

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAllProduct() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    public void addProduct(ProductDto productDto) {
        productRepository.save(modelMapper.map(productDto, Product.class));
    }

    public void updateProduct(Long id, String productName, String productType, long productPrice, String productIngredients) {
        productRepository.updateProductRepo(id, productName, productType, productPrice, productIngredients);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
