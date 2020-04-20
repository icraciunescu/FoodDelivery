package ro.mxp.food.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ro.mxp.food.entity.Cart;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, Long> {

    @Override
    List<Cart> findAll();

    @Transactional
    @Modifying
    @Query("update Cart c set c.quantity=:quantity where c.id=:id")
    void updateCarRepo(@Param("id") Long id, @Param("quantity") int quantity);

}
