package eu.filip.backend.config;

import eu.filip.backend.entity.User;
import eu.filip.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.RememberMeServices;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

@AllArgsConstructor
public class RememberService implements RememberMeServices {
    @Autowired
    AuthenticationManager authenticationManager;

    private UserService userService;

    @Override
    public Authentication autoLogin(HttpServletRequest request, HttpServletResponse response) {
        Cookie hash = null;
        Cookie[] cookies = request.getCookies();
        for(Cookie c : cookies){
            if(c.getName().equals("remember-me")){
                hash = c;
            }
        }

        Base64.Decoder decoder = Base64.getDecoder();
        Base64.Encoder encoder = Base64.getEncoder();

        byte[] decoded = decoder.decode(hash.getValue());
        String decodedString = new String(decoded);

        String[] splited = decodedString.split(":");

        User user = userService.getUserByUsername(splited[0]);

        MessageDigest messageDigest = null;
        try{
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (Exception e){}
        //TODO: HEX FROM username:expirateTime:password:key
        String md5CreationData = "filip:2592000000:1234:secret";
        messageDigest.update(md5CreationData.getBytes());
        byte[] digest = messageDigest.digest();
        String hashString = DatatypeConverter.printHexBinary(digest).toUpperCase();

        String str = "filip:2592000000:" + hashString;

        byte[] bytes = encoder.encode(str.getBytes(StandardCharsets.UTF_8));
        String hashStr = new String(bytes);





        if(hash.getValue().equals(hashStr)){
            System.out.println("hashes match");
            RememberMeAuthenticationToken rememberMeAuthenticationToken = new RememberMeAuthenticationToken(
                    "secret",
                    user,
                    user.getAuthorities()
            );

            return authenticationManager.authenticate(rememberMeAuthenticationToken);
        }

        return null;
    }

    @Override
    public void loginFail(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("FAIL");
    }

    @Override
    public void loginSuccess(HttpServletRequest request, HttpServletResponse response, Authentication successfulAuthentication) {
    }
}
