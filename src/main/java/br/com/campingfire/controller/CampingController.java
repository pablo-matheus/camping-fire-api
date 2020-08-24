package br.com.campingfire.controller;

import br.com.campingfire.request.CampingRequest;
import br.com.campingfire.response.CampingResponse;
import br.com.campingfire.response.IdResponse;
import br.com.campingfire.enums.State;
import br.com.campingfire.model.Camping;
import br.com.campingfire.service.CampingService;
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
@RequestMapping("v1/campings")
public class CampingController {

    private final CampingService campingService;

    @GetMapping
    public ResponseEntity<List<CampingResponse>> listAll(
            @RequestParam(required = false) State state,
            @RequestParam(required = false) String city)
    {

        //TODO Refactor conditions

        if (state != null && city != null) {

            return ResponseEntity.ok(campingService.findAllByStateAndCity(state, city)
                .stream()
                .map(CampingResponse::new)
                .collect(Collectors.toList()));

        }

        if (state != null) {

            return ResponseEntity.ok(campingService.findAllByState(state)
                    .stream()
                    .map(CampingResponse::new)
                    .collect(Collectors.toList()));

        }

        if (city != null) {

            return ResponseEntity.ok(campingService.findAllByCity(city)
                    .stream()
                    .map(CampingResponse::new)
                    .collect(Collectors.toList()));

        }

        return ResponseEntity.ok(campingService.findAll()
                .stream()
                .map(CampingResponse::new)
                .collect(Collectors.toList()));

    }

    @GetMapping("/{id}")
    public ResponseEntity<CampingResponse> getById(@PathVariable Long id) {

        return ResponseEntity.ok(new CampingResponse(campingService.findById(id)));

    }

    @PostMapping
    public ResponseEntity<IdResponse> saveCampingRequest(
            @RequestBody @Valid CampingRequest campingRequest,
            UriComponentsBuilder uriBuilder)
    {

        Camping camping = campingService.saveCampRequest(campingRequest);
        URI uri = uriBuilder.build("api/camp");
        return ResponseEntity.created(uri).body(new IdResponse(camping.getId()));

    }

    @PutMapping("/{id}")
    public ResponseEntity<IdResponse> updateCamping(
            @RequestBody @Valid CampingRequest campingRequest,
            @PathVariable Long id)
    {

        Camping camping = campingService.editCamp(campingRequest, id);
        return ResponseEntity.ok(new IdResponse(camping.getId()));

    }

    @DeleteMapping("/{id}")
    //TODO Turn into void, the Id always will be 0
    public ResponseEntity<IdResponse> deleteCamping(@PathVariable Long id) {

        //TODO Return ResponseEntity.notFound() when camp does not located
        campingService.delete(id);
        return ResponseEntity.ok(new IdResponse(id));

    }

}
