package eu.filip.backend.controller;

import eu.filip.backend.entity.Post;
import eu.filip.backend.entity.PostPage;
import eu.filip.backend.entity.User;
import eu.filip.backend.repository.PostRepository;
import eu.filip.backend.repository.UserRepository;
import eu.filip.backend.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class Controller {

    private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<Page<Post>> posts(PostPage postPage){
        return new ResponseEntity<>(postService.getPosts(postPage), HttpStatus.ACCEPTED);
    }
}
