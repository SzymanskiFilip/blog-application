package eu.filip.backend.controller;

import eu.filip.backend.dto.LoginCredentials;
import eu.filip.backend.dto.UsernameDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Slf4j
@RestController
public class LoginController {

    @PostMapping("/login")
    public void login(@RequestBody LoginCredentials loginCredentials){
        System.out.println(loginCredentials.getPassword());
    }

    @PostMapping("/authenticated")
    public boolean authenticated(Authentication authentication){
        if(authentication != null){
            if(authentication.isAuthenticated()){
                return true;
            }
        }
        return false;
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) throws Exception{
        log.info("Logging out!");
        request.logout();
    }

}
