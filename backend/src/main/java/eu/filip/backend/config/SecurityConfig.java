package eu.filip.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.filip.backend.repository.UserRepository;
import eu.filip.backend.service.UserDetailsServiceImpl;
import eu.filip.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.RememberMeConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@AllArgsConstructor
public class SecurityConfig{

    private UserRepository userRepository;
    private ObjectMapper objectMapper;
    private UserService userService;

    @Autowired
    AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsServiceImpl userDetailsService(){
        return new UserDetailsServiceImpl(userRepository);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
        return authenticationManager;
    }

    @Bean
    public RememberMeAuthenticationProvider rememberMeAuthenticationProvider(){
        RememberMeAuthenticationProvider provider = new RememberMeAuthenticationProvider("secret");
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable();
        httpSecurity
                .cors()
                .and()
                .addFilterBefore(corsFilter(), SessionManagementFilter.class)
                .authorizeRequests()
                .antMatchers("/post/*").permitAll()
                .antMatchers("/posts").permitAll()
                .antMatchers("/authenticated").permitAll()
                .antMatchers("/check").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/check-availability").permitAll()
                .antMatchers("/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/z")
                .and()
                .addFilter(loginFilter())
                .addFilter(rememberFilter())
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));


        return httpSecurity.build();
    }

    public LoginFilter loginFilter() throws Exception{
        LoginFilter loginFilter = new LoginFilter(objectMapper, rememberService());
        loginFilter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
        return loginFilter;
    }

    public RememberFilter rememberFilter() throws Exception{
        RememberFilter rememberFilter = new RememberFilter(authenticationManager(authenticationConfiguration), rememberService());
        return rememberFilter;
    }

    public RememberService rememberService() throws Exception {
        RememberService rememberService = new RememberService(authenticationManager(authenticationConfiguration), userService);
        return rememberService;
    }


    public CorsFilter corsFilter(){
        return new CorsFilter();
    }


}
