package br.com.camping.controller;

import br.com.camping.dto.IdResponseDTO;
import br.com.camping.dto.UserRequestDTO;
import br.com.camping.dto.UserResponseDTO;
import br.com.camping.model.User;
import br.com.camping.service.UserService;
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
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listAll() {

        return ResponseEntity.ok(userService.findAll()
                .stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList()));

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id) {

        return ResponseEntity.ok(new UserResponseDTO(userService.findById(id)));

    }

    @PostMapping
    public ResponseEntity<IdResponseDTO> saveUserRequest(
            @RequestBody @Valid UserRequestDTO userRequestDTO,
            UriComponentsBuilder uriBuilder)
    {

        User user = userService.saveUserRequest(userRequestDTO);
        URI uri = uriBuilder.build("api/user");
        return ResponseEntity.created(uri).body(new IdResponseDTO(user.getId()));

    }

    @PutMapping("/{id}")
    public ResponseEntity<IdResponseDTO> updateUser(
            @RequestBody @Valid UserRequestDTO userRequestDTO,
            @PathVariable Long id)
    {

        User user = userService.editUser(userRequestDTO, id);
        return ResponseEntity.ok(new IdResponseDTO(user.getId()));

    }

    @DeleteMapping("/{id}")
    //TODO Turn into void, the Id always will be 0
    public ResponseEntity<IdResponseDTO> delete(@PathVariable Long id) {

        //TODO Return ResponseEntity.notFound() when user does not located
        userService.delete(id);
        return ResponseEntity.ok(new IdResponseDTO(id));

    }

}
