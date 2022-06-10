package eu.filip.backend.service;

import eu.filip.backend.entity.User;
import eu.filip.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
