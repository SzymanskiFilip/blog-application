package eu.filip.backend.controller;

import eu.filip.backend.dto.LoginCredentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @CrossOrigin
    @PostMapping("/login")
    public void login(@RequestBody LoginCredentials loginCredentials){
        System.out.println(loginCredentials.getPassword());
    }

}
