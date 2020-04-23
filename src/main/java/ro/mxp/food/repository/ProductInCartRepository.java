package ro.mxp.food.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ro.mxp.food.entity.ProductInCart;

import java.util.List;

public interface ProductInCartRepository extends CrudRepository<ProductInCart, Long> {

    @Override
    List<ProductInCart> findAll();

    @Transactional
    @Modifying
    @Query("update ProductInCart c set c.quantityProduct=:quantity where c.id=:id")
    void updateProductInCartRepo(@Param("id") Long id, @Param("quantity") int quantity);

}
