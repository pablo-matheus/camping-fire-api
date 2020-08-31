package br.com.campingfire.controller;

import br.com.campingfire.model.Image;
import br.com.campingfire.response.IdResponse;
import br.com.campingfire.response.ImageResponse;
import br.com.campingfire.service.ImageService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("v1/images")
public class ImageController {

    private final ImageService imageService;

    @ApiOperation("Submit New Image")
    @PostMapping
    public ResponseEntity<IdResponse> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("campingId") Long id,
            UriComponentsBuilder uriBuilder) throws IOException
    {

        //TODO Limit accepted data types

        Image image = imageService.saveRequest(file, id);
        return ResponseEntity.created(uriBuilder.build("v1/images"))
                .body(new IdResponse(image.getId()));

    }

    @ApiOperation("Retrieve Image")
    @GetMapping("/{id}")
    public ResponseEntity<ImageResponse> getById(@PathVariable Long id) {

        return ResponseEntity.ok(new ImageResponse(imageService.findById(id)));

    }

    @ApiOperation("Retrieve Camping Image List")
    @GetMapping("/campings/{id}")
    public ResponseEntity<List<ImageResponse>> ListAllByCampingId(@PathVariable Long id) {

        return ResponseEntity.ok(imageService.findAllByCampingId(id)
                .stream()
                .map(ImageResponse::new)
                .collect(Collectors.toList()));

    }

    //TODO Implement other methods

}
