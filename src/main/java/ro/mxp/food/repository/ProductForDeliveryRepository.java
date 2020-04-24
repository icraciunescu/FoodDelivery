package ro.mxp.food.repository;

import org.springframework.data.repository.CrudRepository;
import ro.mxp.food.entity.ProductForDelivery;

import java.util.List;

public interface ProductForDeliveryRepository extends CrudRepository<ProductForDelivery, Long> {

    @Override
    List<ProductForDelivery> findAll();

}
