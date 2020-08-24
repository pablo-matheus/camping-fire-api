package br.com.camping.service;

import br.com.camping.request.UserRequest;
import br.com.camping.model.User;
import br.com.camping.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {

        return userRepository.findAll();

    }

    public User findById(Long id) {

        return userRepository.findById(id).orElse(null);

    }

    public User save(User user) {

        return userRepository.save(user);

    }

    public User saveUserRequest(UserRequest userRequest) {

        return this.save(new User(userRequest));

    }

    public User editUser(UserRequest userRequest, Long id) {

        User user = new User(userRequest);
        user.setId(id);
        return this.save(user);

    }

    public void delete(Long id) {

        userRepository.deleteById(id);

    }

}
