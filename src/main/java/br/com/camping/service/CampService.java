package br.com.camping.service;

import br.com.camping.dto.CampRequestDTO;
import br.com.camping.enums.State;
import br.com.camping.model.Camp;
import br.com.camping.repository.CampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CampService {

    private final CampRepository campRepository;

    private final UserService userService;

    public List<Camp> findAll() {

        return campRepository.findAll();

    }

    public List<Camp> findAllByState(State state) {

        return campRepository.findAllByState(state);

    }

    public List<Camp> findAllByCity(String city) {

        return campRepository.findAllByCity(city);

    }

    public List<Camp> findAllByStateAndCity(State state, String city) {

        return campRepository.findAllByStateAndCity(state, city);

    }

    public Camp findById(Long id) {

        return campRepository.findById(id).orElse(null);

    }

    public Camp save(Camp camp) {

        return campRepository.save(camp);

    }

    public Camp saveCampRequest(CampRequestDTO campRequestDTO) {

        Camp camp = new Camp(campRequestDTO);
        camp.setUser(userService.findById(campRequestDTO.getUserId()));
        return campRepository.save(camp);

    }

    public Camp editCamp(CampRequestDTO campRequestDTO, Long id) {

        Camp camp = new Camp(campRequestDTO);
        camp.setId(id);
        camp.setUser(userService.findById(campRequestDTO.getUserId()));

        return this.save(camp);

    }

    public void delete(Long id) {

        campRepository.deleteById(id);

    }

}
