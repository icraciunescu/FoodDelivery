package ro.mxp.food.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ro.mxp.food.entity.Restaurant;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

    Restaurant findByUsername(String string);

    @Override
    List<Restaurant> findAll();

    @Transactional
    @Modifying
    @Query("update Restaurant r set r.email=:email, r.username=:username, r.restaurantName=:restaurantName, r.restaurantSpecificity=:restaurantSpecificity," +
            "r.restaurantAddress=:restaurantAddress, r.restaurantPhone=:restaurantPhone where r.id=:id")
    void updateRestaurantRepo(@Param("id") Long id, @Param("email") String email, @Param("username") String username, @Param("restaurantName") String restaurantName,
                              @Param("restaurantSpecificity") String restaurantSpecificity, @Param("restaurantAddress") String restaurantAddress, @Param("restaurantPhone") String restaurantPhone);

}
