package br.com.campingfire.repository;

import br.com.campingfire.enums.State;
import br.com.campingfire.model.Camping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampingRepository extends JpaRepository<Camping, Long> {

    //TODO Change Page return to List or Slice?

    /** Query to find all camps searching by state */
    Page<Camping> findAllByState(State state, Pageable pageable);

    /** Query to find all camps searching by city */
    Page<Camping> findAllByCity(String city, Pageable pageable);

    /** Query to find all camps searching by state and city */
    Page<Camping> findAllByStateAndCity(State state, String city, Pageable pageable);

    /** Query to find all camps of an user */
    Page<Camping> findAllByUserId(Long id, Pageable pageable);

}
