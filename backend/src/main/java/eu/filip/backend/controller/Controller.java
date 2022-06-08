package eu.filip.backend.controller;

import eu.filip.backend.entity.Like;
import eu.filip.backend.entity.Post;
import eu.filip.backend.entity.PostPage;
import eu.filip.backend.entity.User;
import eu.filip.backend.repository.LikeRepository;
import eu.filip.backend.repository.PostRepository;
import eu.filip.backend.repository.UserRepository;
import eu.filip.backend.service.LikeService;
import eu.filip.backend.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
public class Controller {

    private final PostService postService;
    private final LikeService likeService;

    @GetMapping("/posts")
    public ResponseEntity<Page<Post>> posts(PostPage postPage){
        return new ResponseEntity<>(postService.getPosts(postPage), HttpStatus.ACCEPTED);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Post> post(@PathVariable("id") Long id){
        boolean authenticated = false;
        if(authenticated){
            log.info("USER WITH DETAILS");
            //return ResponseEntity.ok(postService.getPostForAuthenticated(postId, userId));
            //handle no value present
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postService.getPost(id));
    }

    @GetMapping("/likes")
    public ResponseEntity<?> like(){

        Long postId = 3L, userId = 1L;

        likeService.like(postId, userId);

        return null;
    }
}
