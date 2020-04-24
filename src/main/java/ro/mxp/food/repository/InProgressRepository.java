package ro.mxp.food.repository;

import org.springframework.data.repository.CrudRepository;
import ro.mxp.food.entity.InProgress;

import java.util.List;

public interface InProgressRepository extends CrudRepository<InProgress, Long> {

    @Override
    List<InProgress> findAll();

}
