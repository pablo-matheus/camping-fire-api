package br.com.campingfire.service;

import br.com.campingfire.request.CampingEditRequest;
import br.com.campingfire.request.CampingSubmitRequest;
import br.com.campingfire.enums.State;
import br.com.campingfire.model.Camping;
import br.com.campingfire.repository.CampingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CampingService {

    private final CampingRepository campingRepository;

    private final UserService userService;

    //TODO Change Camping JPA Object to CampingDTO

    public Page<Camping> findAll(Pageable pageable) {

        return campingRepository.findAll(pageable);

    }

    public Page<Camping> findAllByState(State state, Pageable pageable) {

        return campingRepository.findAllByState(state, pageable);

    }

    public Page<Camping> findAllByCity(String city, Pageable pageable) {

        return campingRepository.findAllByCity(city, pageable);

    }

    public Page<Camping> findAllByStateAndCity(State state, String city, Pageable pageable) {

        return campingRepository.findAllByStateAndCity(state, city, pageable);

    }

    public Camping findById(Long id) {

        return campingRepository.findById(id).get();

    }

    public Camping save(Camping camping) {

        return campingRepository.save(camping);

    }

    public Camping saveCampRequest(CampingSubmitRequest campingSubmitRequest, Long userId) {

        //TODO Remove this
        if (userId == null) {
            userId = 1L;
        }

        Camping camping = new Camping(campingSubmitRequest);
        camping.setUser(userService.findById(userId));
        return campingRepository.save(camping);

    }

    public Camping editCamp(CampingEditRequest campingEditRequest, Long id, Long userId) {

        //TODO Remove this
        if (userId == null) {

            userId = 1L;

        }

        Camping camping = new Camping(campingEditRequest);
        camping.setId(id);
        camping.setUser(userService.findById(userId));

        return this.save(camping);

    }

    public void delete(Long id) {

        campingRepository.deleteById(id);

    }

}
