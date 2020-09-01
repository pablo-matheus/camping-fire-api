package br.com.campingfire.repository;

import br.com.campingfire.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    /** Query to find all images of a camp */
    List<Image> findAllByCampingId(Long id);

    boolean existsByCampingId(Long id);

}
