package br.com.camping.controller;

import br.com.camping.response.IdResponse;
import br.com.camping.request.UserRequest;
import br.com.camping.response.UserResponse;
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
@RequestMapping("v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> listAll() {

        return ResponseEntity.ok(userService.findAll()
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList()));

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {

        return ResponseEntity.ok(new UserResponse(userService.findById(id)));

    }

    @PostMapping
    public ResponseEntity<IdResponse> saveUserRequest(
            @RequestBody @Valid UserRequest userRequest,
            UriComponentsBuilder uriBuilder)
    {

        User user = userService.saveUserRequest(userRequest);
        URI uri = uriBuilder.build("api/user");
        return ResponseEntity.created(uri).body(new IdResponse(user.getId()));

    }

    @PutMapping("/{id}")
    public ResponseEntity<IdResponse> updateUser(
            @RequestBody @Valid UserRequest userRequest,
            @PathVariable Long id)
    {

        User user = userService.editUser(userRequest, id);
        return ResponseEntity.ok(new IdResponse(user.getId()));

    }

    @DeleteMapping("/{id}")
    //TODO Turn into void, the Id always will be 0
    public ResponseEntity<IdResponse> deleteUser(@PathVariable Long id) {

        //TODO Return ResponseEntity.notFound() when user does not located
        userService.delete(id);
        return ResponseEntity.ok(new IdResponse(id));

    }

    //TODO Get user campings /users/1/campings/1

}
