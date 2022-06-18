package eu.filip.backend.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PostDto {
    private Long id;
    private Long creator_id;
    private String title;
    private String body;
    private String image_name;
    private int likes;
    private boolean liked_status;

    public PostDto(Long id, Long creator_id, String title, String body, String image_name, int likes, boolean liked_status) {
        this.id = id;
        this.creator_id = creator_id;
        this.title = title;
        this.body = body;
        this.image_name = image_name;
        this.likes = likes;
        this.liked_status = liked_status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(Long creator_id) {
        this.creator_id = creator_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isLiked_status() {
        return liked_status;
    }

    public void setLiked_status(boolean liked_status) {
        this.liked_status = liked_status;
    }
}
