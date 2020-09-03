package br.com.campingfire.service;

import br.com.campingfire.model.User;
import br.com.campingfire.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expiration;

    @Autowired
    public AuthenticationService(UserRepository userRepository) {

        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByEmail(username);

        if (user.isPresent()) {

           return user.get();

        }

        throw new UsernameNotFoundException("User not found");

    }

    public String generateToken(Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        Date todayDate = new Date();
        Date expirationDate = new Date(todayDate.getTime() + Long.parseLong(this.expiration));

        return Jwts.builder()
                .setIssuer("Camping Fire API")
                .setSubject(user.getId().toString())
                .setIssuedAt(todayDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

    }

    public boolean isValidToken(String token) {

        try {

            Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(token);

            return true;

        } catch (Exception e) {

            return false;

        }

    }

    public Long getUserId(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(this.secret)
                .parseClaimsJws(token).getBody();

        return Long.parseLong(claims.getSubject());

    }

}
