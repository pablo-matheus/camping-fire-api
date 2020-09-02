package br.com.campingfire.service;

import br.com.campingfire.request.UserSubmitRequest;
import br.com.campingfire.model.User;
import br.com.campingfire.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    //TODO Change User JPA Object to UserDTO

    public List<User> findAll() {

        return userRepository.findAll();

    }

    public User findById(Long id) {

        return userRepository.findById(id).orElse(null);

    }

    public User findByEmail(String email) {

        return userRepository.findByEmail(email).get();

    }

    public User save(User user) {

        return userRepository.save(user);

    }

    public User saveUserRequest(UserSubmitRequest userSubmitRequest) {

        User user = new User(userSubmitRequest);
        user.setPassword(passwordEncoder.encode(userSubmitRequest.getPassword()));
        return this.save(user);

    }

    public User editUser(UserSubmitRequest userSubmitRequest, Long id) {

        User user = new User(userSubmitRequest);
        user.setId(id);
        return this.save(user);

    }

    public void delete(Long id) {

        userRepository.deleteById(id);

    }

}
