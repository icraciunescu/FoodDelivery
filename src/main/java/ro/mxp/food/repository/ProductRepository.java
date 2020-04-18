package ro.mxp.food.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ro.mxp.food.entity.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Override
    List<Product> findAll();

    @Transactional
    @Modifying
    @Query("update Product p set p.productName=:productName, p.productType=:productType, p.productPrice=:productPrice, " +
            "p.productIngredients=:productIngredients where p.id=:id")
    void updateProductRepo(@Param("id") Long id, @Param("productName") String productName, @Param("productType") String productType, @Param("productPrice") Long productPrice,
                           @Param("productIngredients") String productIngredients);

}
