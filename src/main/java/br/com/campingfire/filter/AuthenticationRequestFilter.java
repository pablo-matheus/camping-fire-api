package br.com.campingfire.filter;

import br.com.campingfire.model.User;
import br.com.campingfire.repository.UserRepository;
import br.com.campingfire.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class AuthenticationRequestFilter extends OncePerRequestFilter {

    private AuthenticationService authenticationService;

    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException
    {

        String token = this.retrieveToken(request);

        if(authenticationService.isValidToken(token)) {

            authenticateUser(token);

        }

        filterChain.doFilter(request, response);

    }

    private String retrieveToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {

            return null;

        }

        return token.substring(7, token.length());

    }

    private void authenticateUser(String token) {

        Long userId = authenticationService.getUserId(token);
        User user = userRepository.findById(userId).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

}
