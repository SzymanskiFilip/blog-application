package eu.filip.backend.service;

import eu.filip.backend.dto.RegisterDataDto;
import eu.filip.backend.entity.User;
import eu.filip.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public boolean doesUserExist(String username){
        User user = getUserByUsername(username);
        if(user != null){
            return true;
        }
        return false;
    }

    private boolean doesEmailExist(String email){
        User user = userRepository.findByEmail(email);
        if(user != null){
            return true;
        }
        return false;
    }

    public ResponseEntity<?> registerUser(RegisterDataDto registerDataDto){
        if(registerDataDto.getPassword().length() < 6){
            return ResponseEntity.badRequest().body("The password has to be at least 6 characters");
        }

        if(doesEmailExist(registerDataDto.getEmail())){
            return ResponseEntity.badRequest().body("Email already in use");
        }

        User user = new User();
        user.setUsername(registerDataDto.getUsername());
        user.setPassword(registerDataDto.getPassword());
        user.setEmail(registerDataDto.getEmail());
        user.setRole("USER");

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Registered successfully");
    }
}
