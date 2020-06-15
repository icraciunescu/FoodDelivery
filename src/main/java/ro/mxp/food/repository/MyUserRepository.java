package ro.mxp.food.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ro.mxp.food.entity.MyUser;


import java.util.List;

public interface MyUserRepository extends CrudRepository<MyUser, Long> {

    MyUser findByUsername(String username);

    @Override
    List<MyUser> findAll();

    @Transactional
    @Modifying
    @Query("update MyUser m set m.email = :email, m.username = :username where m.id = :id")
    void updateMyUser(@Param("id") Long id, @Param("email") String email, @Param("username") String username);

}
