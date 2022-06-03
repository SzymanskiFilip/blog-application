package eu.filip.backend.controller;

import eu.filip.backend.entity.Post;
import eu.filip.backend.entity.User;
import eu.filip.backend.repository.PostRepository;
import eu.filip.backend.repository.UserRepository;
import eu.filip.backend.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class Controller {

    private final PostService postService;

    @GetMapping("/posts")
    public List<Post> posts(){
        return postService.findAllPosts();
    }
}
