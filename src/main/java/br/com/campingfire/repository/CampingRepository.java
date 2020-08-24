package br.com.campingfire.repository;

import br.com.campingfire.enums.State;
import br.com.campingfire.model.Camping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampingRepository extends JpaRepository<Camping, Long> {

    /** Query to find all camps searching by state */
    List<Camping> findAllByState(State state);

    /** Query to find all camps searching by city */
    List<Camping> findAllByCity(String city);

    /** Query to find all camps searching by state and city */
    List<Camping> findAllByStateAndCity(State state, String city);

}
