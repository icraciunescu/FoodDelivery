package ro.mxp.food.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ro.mxp.food.entity.MasterAdmin;
import ro.mxp.food.entity.User;


import java.util.List;

public interface MasterAdminRepository extends CrudRepository<MasterAdmin, Long> {

    MasterAdmin findByUsername(String username);

    @Override
    List<MasterAdmin> findAll();

    @Transactional
    @Modifying
    @Query("update MasterAdmin m set m.email = :email, m.username = :username, m.password = :password where m.id = :id")
    void updateMasterAdminRepo(@Param("id") Long id, @Param("email") String email, @Param("username") String username, @Param("password") String password);

}
