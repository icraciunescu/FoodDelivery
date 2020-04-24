package ro.mxp.food.repository;

import org.springframework.data.repository.CrudRepository;
import ro.mxp.food.entity.PendingCart;

import java.util.List;

public interface PendingCartRepository extends CrudRepository<PendingCart, Long> {

    @Override
    List<PendingCart> findAll();

}
