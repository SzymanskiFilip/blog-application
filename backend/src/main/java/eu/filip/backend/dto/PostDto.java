package eu.filip.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostDto {
    private Long id;
    private Long creator_id;
    private String title;
    private String body;
    private String image_name;
    private int likes;
    private boolean liked_status;
    private boolean your_post;

    public PostDto(Long id, Long creator_id, String title, String body, String image_name, int likes, boolean liked_status) {
        this.id = id;
        this.creator_id = creator_id;
        this.title = title;
        this.body = body;
        this.image_name = image_name;
        this.likes = likes;
        this.liked_status = liked_status;
    }

}
