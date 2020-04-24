package ro.mxp.food.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.ProductForDeliveryDto;
import ro.mxp.food.repository.ProductForDeliveryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductForDeliveryService {

    private ModelMapper modelMapper = new ModelMapper();

    private ProductForDeliveryRepository productForDeliveryRepository;
    @Autowired
    public ProductForDeliveryService(ProductForDeliveryRepository productForDeliveryRepository) {
        this.productForDeliveryRepository = productForDeliveryRepository;
    }

    public List<ProductForDeliveryDto> getAllProductForDelivery() {
        return productForDeliveryRepository.findAll()
                .stream()
                .map(productForDelivery -> modelMapper.map(productForDelivery, ProductForDeliveryDto.class))
                .collect(Collectors.toList());
    }


}
