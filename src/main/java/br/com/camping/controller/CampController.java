package br.com.camping.controller;

import br.com.camping.dto.CampRequestDTO;
import br.com.camping.dto.CampResponseDTO;
import br.com.camping.dto.IdResponseDTO;
import br.com.camping.enums.State;
import br.com.camping.model.Camp;
import br.com.camping.service.CampService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("api/camp")
public class CampController {

    private final CampService campService;

    @GetMapping
    public ResponseEntity<List<CampResponseDTO>> listAll() {

        return ResponseEntity.ok(campService.findAll()
                .stream()
                .map(CampResponseDTO::new)
                .collect(Collectors.toList()));

    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<CampResponseDTO>> listAllByState(@PathVariable State state) {

        return ResponseEntity.ok(campService.findAllByState(state)
                .stream()
                .map(CampResponseDTO::new)
                .collect(Collectors.toList()));

    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<CampResponseDTO>> listAllByCity(@PathVariable String city) {

        return ResponseEntity.ok(campService.findAllByCity(city)
                .stream()
                .map(CampResponseDTO::new)
                .collect(Collectors.toList()));

    }

    @GetMapping("/{state}/{city}")
    public ResponseEntity<List<CampResponseDTO>> listAllByStateAndCity(
            @PathVariable State state,
            @PathVariable String city)
    {

        return ResponseEntity.ok(campService.findAllByStateAndCity(state, city)
                .stream()
                .map(CampResponseDTO::new)
                .collect(Collectors.toList()));

    }

    @GetMapping("/{id}")
    public ResponseEntity<CampResponseDTO> getById(@PathVariable Long id) {

        return ResponseEntity.ok(new CampResponseDTO(campService.findById(id)));

    }

    @PostMapping
    public ResponseEntity<IdResponseDTO> saveCampRequest(
            @RequestBody @Valid CampRequestDTO campRequestDTO,
            UriComponentsBuilder uriBuilder)
    {

        Camp camp = campService.saveCampRequest(campRequestDTO);
        URI uri = uriBuilder.build("api/camp");
        return ResponseEntity.created(uri).body(new IdResponseDTO(camp.getId()));

    }

    @PutMapping("/{id}")
    public ResponseEntity<IdResponseDTO> updateCamp(
            @RequestBody @Valid CampRequestDTO campRequestDTO,
            @PathVariable Long id)
    {

        Camp camp = campService.editCamp(campRequestDTO, id);
        return ResponseEntity.ok(new IdResponseDTO(camp.getId()));

    }

    @DeleteMapping("/{id}")
    //TODO Turn into void, the Id always will be 0
    public ResponseEntity<IdResponseDTO> delete(@PathVariable Long id) {

        //TODO Return ResponseEntity.notFound() when camp does not located
        campService.delete(id);
        return ResponseEntity.ok(new IdResponseDTO(id));

    }

}
