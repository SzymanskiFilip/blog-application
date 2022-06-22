package eu.filip.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@AllArgsConstructor
@Jacksonized
@Builder
public class LoginCredentials {
    private String username;
    private String password;
    private boolean remember_me;
}
