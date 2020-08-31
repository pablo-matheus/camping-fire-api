package br.com.campingfire.service;

import br.com.campingfire.request.CampingEditRequest;
import br.com.campingfire.request.CampingSubmitRequest;
import br.com.campingfire.enums.State;
import br.com.campingfire.model.Camping;
import br.com.campingfire.repository.CampingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CampingService {

    private final CampingRepository campingRepository;

    private final UserService userService;

    //TODO Change Camping JPA Object to CampingDTO

    public List<Camping> findAll() {

        return campingRepository.findAll();

    }

    public List<Camping> findAllByState(State state) {

        return campingRepository.findAllByState(state);

    }

    public List<Camping> findAllByCity(String city) {

        return campingRepository.findAllByCity(city);

    }

    public List<Camping> findAllByStateAndCity(State state, String city) {

        return campingRepository.findAllByStateAndCity(state, city);

    }

    public Camping findById(Long id) {

        return campingRepository.findById(id).orElse(null);

    }

    public Camping save(Camping camping) {

        return campingRepository.save(camping);

    }

    public Camping saveCampRequest(CampingSubmitRequest campingSubmitRequest) {

        Camping camping = new Camping(campingSubmitRequest);
        camping.setUser(userService.findById(campingSubmitRequest.getUserId()));
        return campingRepository.save(camping);

    }

    public Camping editCamp(CampingEditRequest campingEditRequest, Long id) {

        Camping camping = new Camping(campingEditRequest);
        camping.setId(id);
        camping.setUser(userService.findById(campingEditRequest.getUserId()));

        return this.save(camping);

    }

    public void delete(Long id) {

        campingRepository.deleteById(id);

    }

}
