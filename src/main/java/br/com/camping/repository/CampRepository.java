package br.com.camping.repository;

import br.com.camping.enums.State;
import br.com.camping.model.Camp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampRepository extends JpaRepository<Camp, Long> {

    /** Query to find all camps searching by state */
    List<Camp> findAllByState(State state);

    /** Query to find all camps searching by city */
    List<Camp> findAllByCity(String city);

    /** Query to find all camps searching by state and city */
    List<Camp> findAllByStateAndCity(State state, String city);

}
