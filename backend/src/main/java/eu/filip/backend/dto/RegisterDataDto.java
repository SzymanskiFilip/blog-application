package eu.filip.backend.dto;

import lombok.Data;

@Data
public class RegisterDataDto {
    private String username;
    private String email;
    private String password;
}
