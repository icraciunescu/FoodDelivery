package ro.mxp.food.repository;

import org.springframework.data.repository.CrudRepository;
import ro.mxp.food.entity.Cart;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, Long> {

    @Override
    List<Cart> findAll();

}
