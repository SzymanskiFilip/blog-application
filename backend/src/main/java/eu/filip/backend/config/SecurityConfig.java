package eu.filip.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.filip.backend.repository.UserRepository;
import eu.filip.backend.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;


@Configuration
@AllArgsConstructor
public class SecurityConfig{

    private UserRepository userRepository;
    private ObjectMapper objectMapper;
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
        return authenticationConfiguration.getAuthenticationManager();
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
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

        return httpSecurity.build();
    }

    public LoginFilter loginFilter() throws Exception{
        LoginFilter loginFilter = new LoginFilter(objectMapper);
        loginFilter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
        return loginFilter;
    }

    public CorsFilter corsFilter(){
        return new CorsFilter();
    }

}
