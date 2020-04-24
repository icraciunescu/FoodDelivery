package ro.mxp.food.repository;

import org.springframework.data.repository.CrudRepository;
import ro.mxp.food.entity.Pending;

import java.util.List;

public interface PendingRepository extends CrudRepository<Pending, Long> {

    @Override
    List<Pending> findAll();

}
