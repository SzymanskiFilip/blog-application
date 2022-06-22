package eu.filip.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.filip.backend.dto.LoginCredentials;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;

    public LoginFilter(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("TRYING TO AUTHENTICATE");
        try {
            BufferedReader reader = request.getReader();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            LoginCredentials authRequest = objectMapper.readValue(sb.toString(), LoginCredentials.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(), authRequest.getPassword()
            );


            setDetails(request, token);
            setPostOnly(true);

            Base64.Encoder encoder = Base64.getEncoder();

            //Hex of the second part of the full hash, use apache commons for future use
            MessageDigest messageDigest = null;
            try{
                messageDigest = MessageDigest.getInstance("MD5");
            } catch (Exception e){}
            //TODO: HEX FROM username:expirateTime:password:key
            String md5CreationData = "filip:2592000000:1234:secret";
            messageDigest.update(md5CreationData.getBytes());
            byte[] digest = messageDigest.digest();
            String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();


            String str = "filip:2592000000:" + hash;

            byte[] bytes = encoder.encode(str.getBytes(StandardCharsets.UTF_8));
            String hashStr = new String(bytes);
            System.out.println("THE HASH IS: " + hashStr);

            Cookie cookie = new Cookie("remember-me", hashStr);
            response.addCookie(cookie);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return this.getAuthenticationManager().authenticate(token);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
