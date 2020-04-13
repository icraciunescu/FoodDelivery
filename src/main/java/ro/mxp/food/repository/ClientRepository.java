package ro.mxp.food.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ro.mxp.food.entity.Client;

import java.util.Date;
import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Long> {

    @Override
    List<Client> findAll();

    @Transactional
    @Modifying
    @Query("update Client c set c.email=:email, c.username=:username, c.password=:password, c.phoneNumber=:phoneNumber, c.firstName=:firstName," +
            "c.lastName=:lastName, c.dateOfBirth=:dateOfBirth where c.id=:id")
    void updateClientRepo(@Param("id") Long id, @Param("email") String email, @Param("username") String username, @Param("password") String password, @Param("phoneNumber") String phoneNumber,
                          @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("dateOfBirth") Date dateOfBirth);

}