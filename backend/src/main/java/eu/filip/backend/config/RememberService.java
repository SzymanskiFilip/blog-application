package eu.filip.backend.config;

import eu.filip.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.RememberMeServices;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class RememberService implements RememberMeServices {

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public Authentication autoLogin(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("AUTO LOGIN");

        Cookie hash = null;
        Cookie[] cookies = request.getCookies();
        for(Cookie c : cookies){
            if(c.getName().equals("remember-me")){
                hash = c;
            }
        }

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decoded = decoder.decode(hash.getValue());
        String decodedString = new String(decoded);
        System.out.println(decodedString);

        /*
        if(hash != null && hash.getValue().equals("1234")){
            RememberMeAuthenticationToken rememberMeAuthenticationToken = new RememberMeAuthenticationToken(
                    hash.getValue(),
                    User user,
            );
            authenticationManager.authenticate();
        }
        */


        return null;
    }

    @Override
    public void loginFail(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public void loginSuccess(HttpServletRequest request, HttpServletResponse response, Authentication successfulAuthentication) {

    }
}
