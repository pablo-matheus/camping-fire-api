package br.com.campingfire.controller;

import br.com.campingfire.response.IdResponse;
import br.com.campingfire.request.UserRequest;
import br.com.campingfire.response.UserResponse;
import br.com.campingfire.model.User;
import br.com.campingfire.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("v1/users")
public class UserController {

    private final UserService userService;

    @ApiOperation("Retrieve User List")
    @GetMapping
    public ResponseEntity<List<UserResponse>> listAll() {

        return ResponseEntity.ok(userService.findAll()
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList()));

    }

    @ApiOperation("Retrieve User")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {

        return ResponseEntity.ok(new UserResponse(userService.findById(id)));

    }

    @ApiOperation("Submit New User")
    @PostMapping
    public ResponseEntity<IdResponse> saveUserRequest(
            @RequestBody @Valid UserRequest userRequest,
            UriComponentsBuilder uriBuilder)
    {

        User user = userService.saveUserRequest(userRequest);

        return ResponseEntity.created(uriBuilder.build("v1/users"))
                .body(new IdResponse(user.getId()));

    }

    @ApiOperation("Edit User")
    @PutMapping("/{id}")
    public ResponseEntity<IdResponse> updateUser(
            @RequestBody @Valid UserRequest userRequest,
            @PathVariable Long id)
    {

        User user = userService.editUser(userRequest, id);
        return ResponseEntity.ok(new IdResponse(user.getId()));

    }

    @ApiOperation("Delete User")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {

        //TODO Return ResponseEntity.notFound() when user does not located
        userService.delete(id);

    }

    //TODO Get user campings /users/1/campings/1

}
