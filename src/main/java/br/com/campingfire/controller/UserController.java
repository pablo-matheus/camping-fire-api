package br.com.campingfire.controller;

import br.com.campingfire.response.IdResponse;
import br.com.campingfire.request.UserSubmitRequest;
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

    //TODO Improve validation messages in response JSON

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
            @RequestBody @Valid UserSubmitRequest userSubmitRequest,
            UriComponentsBuilder uriBuilder)
    {

        User user = userService.saveUserRequest(userSubmitRequest);

        return ResponseEntity.created(uriBuilder.build("v1/users"))
                .body(new IdResponse(user.getId()));

    }

    @ApiOperation("Edit User")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @RequestBody @Valid UserSubmitRequest userSubmitRequest,
            @PathVariable Long id)
    {

        return ResponseEntity.ok(new UserResponse(userService.editUser(userSubmitRequest, id)));

    }

    //TODO Don't delete user if he has campings registered
    @ApiOperation("Delete User")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {

        //TODO Return ResponseEntity.notFound() when user does not located
        //TODO return response entity
        userService.delete(id);

    }

    //TODO Get user campings /users/1/campings/1

}
