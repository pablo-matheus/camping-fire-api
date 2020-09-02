package br.com.campingfire.controller;

import br.com.campingfire.request.LoginRequest;
import br.com.campingfire.response.TokenResponse;
import br.com.campingfire.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthenticationManager authManager;

    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<TokenResponse> authenticate(@RequestBody @Valid LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken loginData = loginRequest.generateAuthenticationToken();

        try {

            Authentication authentication = authManager.authenticate(loginData);
            String token = authenticationService.generateToken(authentication);
            return ResponseEntity.ok(new TokenResponse(token, "Bearer"));

        } catch (AuthenticationException e) {

            return ResponseEntity.badRequest().build();

        }

    }

}
