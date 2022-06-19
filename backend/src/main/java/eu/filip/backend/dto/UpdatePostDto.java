package eu.filip.backend.dto;

import lombok.Data;

@Data
public class UpdatePostDto {
    private Long id;
    private String title;
    private String body;
}
