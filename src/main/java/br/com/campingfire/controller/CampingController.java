package br.com.campingfire.controller;

import br.com.campingfire.request.CampingEditRequest;
import br.com.campingfire.request.CampingSubmitRequest;
import br.com.campingfire.response.CampingResponse;
import br.com.campingfire.response.IdResponse;
import br.com.campingfire.enums.State;
import br.com.campingfire.model.Camping;
import br.com.campingfire.service.CampingService;
import br.com.campingfire.service.ImageService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("v1/campings")
public class CampingController {

    private final CampingService campingService;

    private final ImageService imageService;

    //TODO Improve validation messages in response JSON

    @ApiOperation("Retrieve Camping List")
    @GetMapping
    public ResponseEntity<List<CampingResponse>> listAll(
            @RequestParam(required = false) State state,
            @RequestParam(required = false) String city)
    {

        //TODO Refactor conditionals
        //TODO Remove imageURL and imageService

        if (state != null && city != null) {

            List<CampingResponse> campingList = campingService.findAllByStateAndCity(state, city)
                    .stream()
                    .map(CampingResponse::new)
                    .collect(Collectors.toList());

            campingList.stream().filter(camping -> imageService.existsByCampingId(camping.getId())).forEach(camping -> camping.setImageUrl(imageService.findAllByCampingId(camping.getId()).get(0).getUrl()));
            return ResponseEntity.ok(campingList);

        }

        if (state != null) {

            List<CampingResponse> campingList = campingService.findAllByState(state)
                    .stream()
                    .map(CampingResponse::new)
                    .collect(Collectors.toList());

            campingList.stream().filter(camping -> imageService.existsByCampingId(camping.getId())).forEach(camping -> camping.setImageUrl(imageService.findAllByCampingId(camping.getId()).get(0).getUrl()));
            return ResponseEntity.ok(campingList);

        }

        if (city != null) {

            List<CampingResponse> campingList = campingService.findAllByCity(city)
                    .stream()
                    .map(CampingResponse::new)
                    .collect(Collectors.toList());

            campingList.stream().filter(camping -> imageService.existsByCampingId(camping.getId())).forEach(camping -> camping.setImageUrl(imageService.findAllByCampingId(camping.getId()).get(0).getUrl()));
            return ResponseEntity.ok(campingList);

        }

        List<CampingResponse> campingList = campingService.findAll()
                .stream()
                .map(CampingResponse::new)
                .collect(Collectors.toList());

        campingList.stream().filter(camping -> imageService.existsByCampingId(camping.getId())).forEach(camping -> camping.setImageUrl(imageService.findAllByCampingId(camping.getId()).get(0).getUrl()));
        return ResponseEntity.ok(campingList);

    }

    @ApiOperation("Retrieve Camping")
    @GetMapping("/{id}")
    public ResponseEntity<CampingResponse> getById(@PathVariable Long id) {

        return ResponseEntity.ok(new CampingResponse(campingService.findById(id)));

    }

    @ApiOperation("Submit New Camping")
    @PostMapping
    public ResponseEntity<IdResponse> saveCampingRequest(
            @RequestParam(required = false) Long userId,
            @RequestBody @Valid CampingSubmitRequest campingSubmitRequest,
            UriComponentsBuilder uriBuilder)
    {

        Camping camping = campingService.saveCampRequest(campingSubmitRequest, userId);

        return ResponseEntity.created(uriBuilder.build("v1/campings"))
                .body(new IdResponse(camping.getId()));

    }

    @ApiOperation("Edit Camping")
    @PutMapping("/{id}")
    public ResponseEntity<CampingResponse> updateCamping(
            @RequestBody @Valid CampingEditRequest campingEditRequest,
            @PathVariable Long id,
            @RequestParam(required = false) Long userId)
    {

        return ResponseEntity.ok(new CampingResponse(campingService.editCamp(campingEditRequest, id, userId)));

    }

    @ApiOperation("Delete Camping")
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteCamping(@PathVariable Long id) {

        //TODO Return ResponseEntity.notFound() when camp does not located
        campingService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
